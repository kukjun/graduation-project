package io.wisoft.testermatchingplatform.domain.tester;

import io.wisoft.testermatchingplatform.domain.category.Category;
import io.wisoft.testermatchingplatform.domain.grade.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Tester {
    private Long id;
    private final String email;
    private final String password;
    private final String nickname;
    private final String phoneNumber;
    private final Category preferCategory;
    private final String introMessage;
    private final String introPictureRef;
    private final Grade grade;


}
