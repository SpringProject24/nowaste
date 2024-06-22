package org.nmfw.foodietree.domain.customer.dto.resp;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private String customerId;
    private String nickname;
    private String profileImage;
    private String customerPhoneNumber;
}