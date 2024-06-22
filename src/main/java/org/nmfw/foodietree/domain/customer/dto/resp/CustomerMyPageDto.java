package org.nmfw.foodietree.domain.customer.dto.resp;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.customer.entity.value.PreferredFoodCategory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//@Data
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Builder
public class CustomerMyPageDto {
    private String customerId;
    private String nickname;
    private String profileImage;
    private String customerPhoneNumber;
    private List<String> preferredArea;
    private List<PreferredFoodCategory> preferredFood;

    // 새로운 생성자
    public CustomerMyPageDto(String customerId, String customerPhoneNumber, String nickname, String profileImage, String preferredAreas, String preferredFoods) {
        this.customerId = customerId;
        this.customerPhoneNumber = customerPhoneNumber;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.preferredArea = Arrays.stream(preferredAreas.split(",")).collect(Collectors.toList());
        this.preferredFood = Arrays.stream(preferredFoods.split(","))
                .map(PreferredFoodCategory::fromKoreanName)
                .collect(Collectors.toList());
    }
}
