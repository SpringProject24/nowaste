package org.nmfw.foodietree.domain.reservation.dto.resp;

import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class ReservationDetailDto {
    private String customerId;
    private String nickname;
    private String storeName;
    private String reservationTime;
    private String pickedUpAt;
    private String cancelReservationAt;
}
