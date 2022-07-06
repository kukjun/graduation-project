package io.wisoft.testermatchingplatform.domain.questmaker;

import javax.persistence.*;

@Entity
@Table(name = "QUEST_MAKERS")
public class QuestMakerEntity {

    // 기본키 매핑
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

    @Column(name = "phone_number")
    private String phoneNumber;

    public QuestMakerEntity(String email, String password, String nickname, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }

    public QuestMakerEntity() {

    }

    public QuestMaker toDomain() {
        return new QuestMaker(
                this.id,
                this.email,
                this.password,
                this.nickname,
                this.phoneNumber
        );
    }

    public static QuestMakerEntity from(final QuestMaker questMaker) {
        return new QuestMakerEntity(
                questMaker.getEmail(),
                questMaker.getPassword(),
                questMaker.getNickname(),
                questMaker.getPhoneNumber()
        );
    }

}
