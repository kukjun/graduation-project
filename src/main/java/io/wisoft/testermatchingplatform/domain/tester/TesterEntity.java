package io.wisoft.testermatchingplatform.domain.tester;

import io.wisoft.testermatchingplatform.domain.BaseTimeEntity;
import io.wisoft.testermatchingplatform.domain.category.CategoryEntity;
import io.wisoft.testermatchingplatform.domain.grade.GradeEntity;

import javax.persistence.*;

@Entity
@Table(name = "TESTERS")
public class TesterEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @JoinColumn(name = "category_id")
    @ManyToOne
    private CategoryEntity preferCategory;

    @Column(name = "intro_message")
    private String introMessage;

    @Column(name = "intro_picture_reference")
    private String introPictureReference;

    @JoinColumn(name = "grade_id")
    @ManyToOne
    private GradeEntity grade;

    public TesterEntity(String email, String password, String nickname, String phoneNumber, CategoryEntity preferCategory, String introMessage, String introPictureReference, GradeEntity grade) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.preferCategory = preferCategory;
        this.introMessage = introMessage;
        this.introPictureReference = introPictureReference;
        this.grade = grade;
    }

    public TesterEntity() {

    }

    public Tester toDomain() {
        return new Tester(
                this.id,
                this.email,
                this.password,
                this.nickname,
                this.phoneNumber,
                this.preferCategory.toDomain(),
                this.introMessage,
                this.introPictureReference,
                this.grade.toDomain()
        );
    }

    // 추가 로직, Tester를 TesterEntity와 맞추어 주는 과정 필요
    public void SynchronizeFromDomain(Tester tester) {
        this.email = tester.getEmail();
        this.password = tester.getPassword();
        this.nickname = tester.getNickname();
        this.phoneNumber = tester.getPhoneNumber();
        this.preferCategory = CategoryEntity.from(tester.getPreferCategory());
        this.introMessage = tester.getIntroMessage();
        this.introPictureReference = tester.getIntroPictureReference();
    }

    public static TesterEntity from(Tester tester) {
        return new TesterEntity(
                tester.getEmail(),
                tester.getPassword(),
                tester.getNickname(),
                tester.getPhoneNumber(),
                CategoryEntity.from(tester.getPreferCategory()),
                tester.getIntroMessage(),
                tester.getIntroPictureReference(),
                GradeEntity.from(tester.getGrade())
        );
    }

}
