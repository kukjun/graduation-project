package io.wisoft.testermatchingplatform.domain.tester;

import io.wisoft.testermatchingplatform.domain.category.Category;
import io.wisoft.testermatchingplatform.domain.grade.Grade;
import lombok.Getter;

@Getter
public class Tester {

    private Long id;
    private String email;
    private String nickname;
    private String phoneNumber;
    private Category preferCategory;
    private String introMessage;
    private String introPictureReference;
    private Grade grade;

    public Tester(Long id, String email, String nickname, String phoneNumber, Category preferCategory, String introMessage, String introPictureReference, Grade grade) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.preferCategory = preferCategory;
        this.introMessage = introMessage;
        this.introPictureReference = introPictureReference;
        this.grade = grade;
    }

    // 테스터 확인 로직이 필요함.
    // id 가 같은 경우, password 도 일치하는지 확인하는 메서드 필요



}
