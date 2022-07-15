package io.wisoft.testermatchingplatform.domain.tester;

import io.wisoft.testermatchingplatform.domain.category.Category;
import io.wisoft.testermatchingplatform.domain.grade.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class Tester {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;
    private Category preferCategory;
    private String introMessage;
    private String introPictureRef;
    private Grade grade;

    private Timestamp registerTime;

    public Tester(String email, String password, String nickname, String phoneNumber, Category preferCategory, String introMessage, String introPictureRef, Grade grade, Timestamp registerTime) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.preferCategory = preferCategory;
        this.introMessage = introMessage;
        this.introPictureRef = introPictureRef;
        this.grade = grade;
        this.registerTime = registerTime;
    }


}
