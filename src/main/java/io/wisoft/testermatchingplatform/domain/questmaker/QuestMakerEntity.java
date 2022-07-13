package io.wisoft.testermatchingplatform.domain.questmaker;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "quest_maker")
@NoArgsConstructor
public class QuestMakerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;
    private Date registerTime;

    public QuestMakerEntity(Long id, String email, String password, String nickname, String phoneNumber,Date registerTime) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.registerTime = registerTime;
    }

    public QuestMaker toDomain(){
        return new QuestMaker(
                this.id,
                this.email,
                this.password,
                this.nickname,
                this.phoneNumber,
                this.registerTime
        );
    }

    public static QuestMakerEntity from(final QuestMaker questMaker){
        return new QuestMakerEntity(
                questMaker.getId(),
                questMaker.getEmail(),
                questMaker.getPassword(),
                questMaker.getNickname(),
                questMaker.getPhoneNumber(),
                questMaker.getRegisterTime()
        );
    }

}
