package myproject.Gummi.domain.entity;

public enum Category {

    HOSPITAL("병원"),
    BEAUTY("미용"),
    INSURANCE("보험"),
    FOOD("밥, 간식"),
    HOTELLING("호텔링"),
    ETC("기타");

    private String title;

    Category(String title){this.title = title;}

    public String getTitle() {return title;}
}
