package io.wisoft.testermatchingplatform.domain.reportpolicy;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReportPolicy {
    private Long id;
    private String category;
    private String content;
    private Long penalty;
}
