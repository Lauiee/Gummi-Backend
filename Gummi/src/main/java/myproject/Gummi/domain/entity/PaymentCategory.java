package myproject.Gummi.domain.entity;

import lombok.Getter;

@Getter
public enum PaymentCategory {

    HOSPITAL("병원"),
    BEAUTY("미용"),
    INSURANCE("보험"),
    FOOD("밥, 간식"),
    HOTELLING("호텔링"),
    ETC("기타");

    private String title;

    PaymentCategory(String title){this.title = title;}

}
