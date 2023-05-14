package mb.io.instacarwrite.model.enums;

public enum PostStatus {

    PUBLISHED(1),
    CREATED(2),
    ARCHIVED(3);


    private int value;

    public int getValue(){
        return this.value;
    }
    PostStatus(int value) {
        this.value = value;
    }
}
