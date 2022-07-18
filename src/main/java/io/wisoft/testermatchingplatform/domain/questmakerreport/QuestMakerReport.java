package io.wisoft.testermatchingplatform.domain.questmakerreport;

import io.wisoft.testermatchingplatform.domain.BaseTime;
import io.wisoft.testermatchingplatform.domain.apply.Apply;
import io.wisoft.testermatchingplatform.domain.reportpolicy.ReportPolicy;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "quest_maker_report")
@NoArgsConstructor
public class QuestMakerReport extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLY_ID")
    private Apply apply;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORT_ID")
    private ReportPolicy report;
    private String title;
    private boolean result;

    public QuestMakerReport(Apply apply, ReportPolicy report, String title, boolean result) {
        this.apply = apply;
        this.report = report;
        this.title = title;
        this.result = result;
    }
}
