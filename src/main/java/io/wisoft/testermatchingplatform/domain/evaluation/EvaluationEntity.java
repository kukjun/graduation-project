package io.wisoft.testermatchingplatform.domain.evaluation;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.wisoft.testermatchingplatform.domain.auth.AuthEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "evalutation")
@Entity
@NoArgsConstructor
public class EvaluationEntity {

    @Id
    @Column(name = "Auth_ID")
    private Long id;

    @JoinColumn(name = "AUTH_ID")
    @OneToOne
    @MapsId
    @JsonManagedReference
    private AuthEntity authId;
    private Long starPoint;
    private String comment;

    public static EvaluationEntity from(final Evaluation evaluation){
        return new EvaluationEntity(
                AuthEntity.from(evaluation.getAuthId()),
                evaluation.getStarPoint(),
                evaluation.getComment()
        );
    }

    public EvaluationEntity(AuthEntity authId, Long starPoint, String comment) {
        this.authId = authId;
        this.starPoint = starPoint;
        this.comment = comment;
    }

    public Evaluation toDomain(){
        return new Evaluation(
                this.authId == null ? null : this.authId.toDomain(),
                this.starPoint,
                this.comment
        );
    }
}
