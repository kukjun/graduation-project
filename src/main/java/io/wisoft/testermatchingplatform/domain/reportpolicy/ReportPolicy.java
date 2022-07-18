package io.wisoft.testermatchingplatform.domain.reportpolicy;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "report_policy")
@NoArgsConstructor
public class ReportPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private String content;
    private Long penalty;

    public ReportPolicy(String category, String content, Long penalty) {
        this.category = category;
        this.content = content;
        this.penalty = penalty;
    }
}
