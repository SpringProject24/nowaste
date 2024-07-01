package org.nmfw.foodietree.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.reservation.dto.resp.ReservationStatusDto;
import org.nmfw.foodietree.domain.reservation.mapper.ReservationMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationMapper reservationMapper;

    /**
     * 예약 취소 메서드
     * @param reservationId : 취소할 예약의 id
     * @return : 취소가 완료되었는지 여부
     *
     */
    public boolean cancelReservation(int reservationId) {
        reservationMapper.cancelReservation(reservationId);

        ReservationStatusDto dto = reservationMapper.findByReservationId(reservationId);

        return dto.getCancelReservationAt() != null;
    }

    /**
     * 픽업 완료 메서드
     * @param reservationId : 픽업 완료할 예약의 id
     * @return : 픽업 완료가 완료되었는지 여부
     */
    public boolean completePickup(int reservationId) {
        reservationMapper.completePickup(reservationId);

        ReservationStatusDto dto = reservationMapper.findByReservationId(reservationId);

        return dto.getPickedUpAt() != null;
    }


//    픽업, 예약취소 로직
//    픽업 => 예약이 되어있고, 픽업시간이 지나지 않았을 때 픽업 완료 가능
//    픽업시간이 지났다면 => '픽업 시간 내에 픽업하지 않아 예약이 취소 되었습니다 ' 공지
//    예약취소 => 픽업시간이 1시간 이상 남았다면, 바로 취소 가능 (취소 수수료 없음)
//=> 픽업 시간이 1시간 이내로 남았다면, 취소 수수료 50% 고지 후 동의 하면 예약 취소 처리

    /**
     * 픽업시간이 지났는지 확인하는 메서드
     * @param reservationId : 예약 아이디
     * @return : 픽업 완료를 누르는 현재 시점으로부터 픽업시간이 지났다면 true, 아니면 false
     */
    public boolean isPickupAllowed(int reservationId) {

        ReservationStatusDto dto = reservationMapper.findByReservationId(reservationId);

        if (dto == null || dto.getPickupTime() == null || dto.getReservationTime() == null) {
            log.info("dto or pickUpTime or reservationTime is null");
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        return now.isBefore(dto.getPickupTime());
    }

    /**
     * 예약 취소가 가능한 시간인지 확인하는 메서드
     * @param reservationId : 해당 예약 아이디
     * @return : 예약 취소를 누르는 현재 시점으로 부터 픽업시간이 1시간 이내라면 false, 아니면 true
     */
    public boolean isCancelAllowed(int reservationId) {

        ReservationStatusDto dto = reservationMapper.findByReservationId(reservationId);

        if (dto == null || dto.getPickupTime() == null) {
            log.info("dto or pickUpTime is null");
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        return now.isBefore(dto.getPickupTime().minusHours(1));
    }
}
