package org.nmfw.foodietree.domain.customer.entity;

import lombok.*;
import org.nmfw.foodietree.domain.customer.entity.value.IssueCategory;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerIssue {
    private int issueId;
    private String customerId;
    private IssueCategory issueCategory;
    private String issueCompleteAt;
    private String issueText;
    private String cancelIssueAt;
    private String reservationId;
}
