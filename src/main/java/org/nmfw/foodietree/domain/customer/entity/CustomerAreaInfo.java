package org.nmfw.foodietree.domain.customer.entity;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAreaInfo {
    private String customerId;
    private String preferredArea;
}
