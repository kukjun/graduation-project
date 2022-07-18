package io.wisoft.testermatchingplatform.domain.evaluation;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.wisoft.testermatchingplatform.domain.auth.Auth;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "evaluation")
@Entity
@NoArgsConstructor
public class Evaluation {

    @Id
    @Column(name = "Auth_ID")
    private Long id;

    @JoinColumn(name = "AUTH_ID")
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonManagedReference
    private Auth authId;
    private Long starPoint;
    private String comment;

    public Evaluation(Auth authId, Long starPoint, String comment) {
        this.authId = authId;
        this.starPoint = starPoint;
        this.comment = comment;
    }

}
