package org.nmfw.foodietree.domain.customer.entity;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerFoodInfo {
    private String customerId;
    private String preferredFood;
}
