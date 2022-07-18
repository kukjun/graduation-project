package io.wisoft.testermatchingplatform.domain.auth;

import io.wisoft.testermatchingplatform.domain.submit.Submit;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "auth")
@NoArgsConstructor
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBMIT_ID")
    private Submit submit;
    private Long status;
    // 제출 , 미제출, 수정
    private String comment;

    public Auth(Submit submit, Long status, String comment) {
        this.submit = submit;
        this.status = status;
        this.comment = comment;
    }
}
