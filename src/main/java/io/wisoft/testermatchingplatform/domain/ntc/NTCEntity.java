package io.wisoft.testermatchingplatform.domain.ntc;

import javax.persistence.*;

@Entity
@Table(name = "NTCS")
public class NTCEntity {

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

    public NTCEntity(String email, String password, String nickname, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }

    public NTCEntity() {

    }

    public NTC toDomain() {
        return new NTC(
                this.id,
                this.email,
                this.password,
                this.nickname,
                this.phoneNumber
        );
    }

    public static NTCEntity from(final NTC ntc) {
        return new NTCEntity(
                ntc.getEmail(),
                ntc.getPassword(),
                ntc.getNickname(),
                ntc.getPhoneNumber()
        );
    }

}
