package io.wisoft.testermatchingplatform.domain.testerreport;

import io.wisoft.testermatchingplatform.domain.BaseTime;
import io.wisoft.testermatchingplatform.domain.reportpolicy.ReportPolicy;
import io.wisoft.testermatchingplatform.domain.submit.Submit;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "tester_report")
@NoArgsConstructor
public class TesterReport extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "SUBMIT_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Submit submit;
    @JoinColumn(name = "REPORT_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private ReportPolicy report;
    private String title;
    private boolean result;

    public TesterReport(Submit submit, ReportPolicy report, String title, boolean result) {
        this.submit = submit;
        this.report = report;
        this.title = title;
        this.result = result;
    }
}
