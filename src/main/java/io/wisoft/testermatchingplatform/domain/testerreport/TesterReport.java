package io.wisoft.testermatchingplatform.domain.testerreport;


import io.wisoft.testermatchingplatform.domain.reportpolicy.ReportPolicy;
import io.wisoft.testermatchingplatform.domain.submit.Submit;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class TesterReport {

    private Long id;
    private Submit submit;
    private ReportPolicy report;
    private String title;
    private Date registerTime;
    private boolean result;
}
