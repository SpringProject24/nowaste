package org.nmfw.foodietree.domain.customer.dto.resp;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.customer.entity.value.PickUpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Builder
public class MyPageReservationDetailDto {
    private int reservationId;
    private String customerId;
    private String nickname;
    private LocalDateTime reservationTime; // 고객이 예약한 시간
    private LocalDateTime cancelReservationAt; // 고객이 얘약을 취소한 시간 null 가능, 값이 존재한다면 예약취소 된 것
    private LocalDateTime pickedUpAt; // 고객이 픽업한 시간
    private PickUpStatus status; // 고객이 예약을 픽업했는지, 픽업대기중인지, 픽업취소 했는지
    private LocalDateTime pickupTime; // 가게에서 지정한 픽업가능 시간
    private String storeName;
    private String storeImg;
    private int price;
}
