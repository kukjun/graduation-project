package io.wisoft.testermatchingplatform.domain.tester;

import io.wisoft.testermatchingplatform.domain.category.Category;
import io.wisoft.testermatchingplatform.domain.grade.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
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
    private Date registerTime;
}
