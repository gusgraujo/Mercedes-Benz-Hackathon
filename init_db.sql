CREATE DATABASE instacar;
CREATE USER instacar_app WITH PASSWORD 'instacar1234';
GRANT ALL ON ALL TABLES IN SCHEMA public TO instacar_app;
GRANT ALL ON DATABASE instacar TO instacar_app