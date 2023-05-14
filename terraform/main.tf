
locals {
  account = "793295554792"
  env     = "prod"
  region  = "eu-central-1"
  vpc_id  = "vpc-0c916561d9ae866d3"
}

provider "aws" {
  #  version = "~> 4.30"
  region = "eu-central-1"
}

terraform {
  backend "s3" {
    bucket         = "terraform-state-793295554792"
    key            = "gle.tfstate"
    region         = "eu-central-1"
    profile        = "prod"
    dynamodb_table = "gle-lock"
    required_providers {
      aws = {
        source  = "hashicorp/aws"
        version = "~> 4.0"
      }
    }
  }
}


resource "aws_ecr_repository" "ecr" {
  name = "gle-app-${local.env}"
}

resource "aws_ecs_cluster" "gle-cluster" {
  name = "gle-${local.env}"
}

data "aws_secretsmanager_secret_version" "credentials" {
  secret_id = "credentials-${local.env}"
}

resource "aws_ecs_task_definition" "gle-task" {
  family                   = "gle-task-${local.env}"
  network_mode             = "awsvpc"
  cpu                      = 1024
  memory                   = 2048
  requires_compatibilities = ["FARGATE"]
  execution_role_arn       = "arn:aws:iam::793295554792:role/ecs-role"
  task_role_arn            = "arn:aws:iam::793295554792:role/ecs-role"
  container_definitions = jsonencode([
    {
      name      = "gle-app"
      image     = "public.ecr.aws/s6z4s9n0/gle:main"
      cpu       = 1024
      memory    = 2048
      essential = true
      portMappings = [
        {
          containerPort = 80
          hostPort      = 80
        }
      ]
  }])
}



resource "aws_ecs_service" "gle-service" {
  name                               = "gle-service-${local.env}"
  launch_type                        = "FARGATE"
  cluster                            = aws_ecs_cluster.gle-cluster.id
  task_definition                    = aws_ecs_task_definition.gle-task.family
  desired_count                      = 2
  deployment_minimum_healthy_percent = 50
  deployment_maximum_percent         = 200
  health_check_grace_period_seconds  = 0
  enable_execute_command             = true
  force_new_deployment               = false
  wait_for_steady_state              = true
  deployment_controller {
    type = "ECS"
  }
  network_configuration {
    subnets          = ["subnet-06ed71537bf2daab6", "subnet-0829454ca3b261098", "subnet-054d95e4b6d271247"]
    assign_public_ip = false
  }
  load_balancer {
    target_group_arn = aws_lb_target_group.gle-api.arn
    container_name   = "gle-app"
    container_port   = 80
  }
}


resource "aws_lb" "gle-api" {
  name                             = "gle-${local.env}"
  internal                         = false
  load_balancer_type               = "application"
  subnets                          = ["subnet-06ed71537bf2daab6", "subnet-0829454ca3b261098", "subnet-054d95e4b6d271247"]
  enable_deletion_protection       = false
  enable_cross_zone_load_balancing = true
}

resource "aws_lb_target_group" "gle-api" {
  name                 = "gle-${local.env}"
  port                 = 80
  protocol             = "HTTP"
  target_type          = "ip"
  vpc_id               = local.vpc_id
  deregistration_delay = "3"

  health_check {
    protocol            = "HTTP"
    path                = "/"
    matcher             = "200-399"
    healthy_threshold   = 2
    unhealthy_threshold = 2
    interval            = 5
    timeout             = 3
  }
}

resource "aws_lb_listener" "gle-api" {
  load_balancer_arn = aws_lb.gle-api.arn
  protocol          = "HTTP"
  port              = 80
  #  certificate_arn   = var.https_certificate_arn
  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.gle-api.arn
  }
}

#db

resource "aws_db_subnet_group" "gle-db" {
  name       = "gle-db"
  subnet_ids = ["subnet-06ed71537bf2daab6", "subnet-0829454ca3b261098", "subnet-054d95e4b6d271247"]
}

module "gle-postgres" {
  source                    = "github.com/traveloka/terraform-aws-rds-postgres"
  service_name              = "gle-db"
  product_domain            = "gle"
  environment               = local.env
  bastion_security_group_id = "sg-0d9f497e1708a5462"
  instance_class            = "db.t3.micro"
  engine_version            = "15.2"
  description               = ""
  allocated_storage         = 10
  multi_az                  = true

  # Change to valid security group id
  vpc_security_group_ids = [
    "sg-0d9f497e1708a5462"
  ]

  # Change to valid db subnet group name
  db_subnet_group_name = aws_db_subnet_group.gle-db.name

  # Change to valid parameter group name
  parameter_group_name = "default.postgres15"

  maintenance_window      = "Mon:00:00-Mon:03:00"
  backup_retention_period = 0

  skip_final_snapshot = true

  # Change to valid monitoring role arn
  monitoring_role_arn = "arn:aws:iam::793295554792:role/rds-monitoring-role"
}

module "s3_bucket" {
  source = "terraform-aws-modules/s3-bucket/aws"

  bucket = "gle-posts"
  acl    = "public-read"

  control_object_ownership = true
  object_ownership         = "ObjectWriter"

  versioning = {
    enabled = false
  }
}

output "db_password" {
  value       = module.gle-postgres.password
  description = "Postgres master password"
}
