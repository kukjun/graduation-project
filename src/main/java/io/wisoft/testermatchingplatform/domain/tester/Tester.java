package io.wisoft.testermatchingplatform.domain.tester;

import io.wisoft.testermatchingplatform.domain.category.Category;
import io.wisoft.testermatchingplatform.domain.grade.Grade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tester {

    private Long id;
    private String email;

    private String password;
    private String nickname;
    private String phoneNumber;
    private Category preferCategory;
    private String introMessage;
    private String introPictureReference;
    private Grade grade;

    public Tester(Long id, String email, String password, String nickname, String phoneNumber, Category preferCategory, String introMessage, String introPictureReference, Grade grade) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.preferCategory = preferCategory;
        this.introMessage = introMessage;
        this.introPictureReference = introPictureReference;
        this.grade = grade;
    }

    public Tester(String email, String password, String nickname, String phoneNumber, Category preferCategory, String introMessage, String introPictureReference, Grade grade) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.preferCategory = preferCategory;
        this.introMessage = introMessage;
        this.introPictureReference = introPictureReference;
        this.grade = grade;
    }
}
