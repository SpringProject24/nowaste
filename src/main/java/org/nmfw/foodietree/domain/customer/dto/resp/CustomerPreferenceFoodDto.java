package org.nmfw.foodietree.domain.customer.dto.resp;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.customer.entity.value.PreferredFoodCategory;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Builder
public class CustomerPreferenceFoodDto {
    private PreferredFoodCategory preferredFood;

    public void setPreferredFood(String preferredFood) {
        this.preferredFood = PreferredFoodCategory.fromKoreanName(preferredFood);
    }
}
