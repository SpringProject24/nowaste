package org.nmfw.foodietree.domain.customer.dto.resp;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Builder
public class MyPageReservationDetailDto {
    private String customerId;
    private String customerName;
    private String reservationTime; // 고객이 예약한 시간
    private String cancelReservationAt; // 고객이 얘약을 취소한 시간 null 가능, 값이 존재한다면 예약취소 된 것
    private String pickUpTime; // 가게에서 지정한 픽업가능 시간
    private String storeName;
}
