package org.nmfw.foodietree.domain.customer.entity;

import lombok.*;
import org.nmfw.foodietree.domain.customer.entity.value.PreferredFoodCategory;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerFoodInfo {
    private int id;
    private String customerId;
    private PreferredFoodCategory preferredFood;
}
