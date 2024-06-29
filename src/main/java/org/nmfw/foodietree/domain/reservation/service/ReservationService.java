package org.nmfw.foodietree.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.reservation.dto.resp.ReservationStatusDto;
import org.nmfw.foodietree.domain.reservation.mapper.ReservationMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationMapper reservationMapper;

    public boolean cancelReservation(int reservationId) {
        reservationMapper.cancelReservation(reservationId);

        ReservationStatusDto dto = reservationMapper.findByReservationId(reservationId);

        return dto.getCancelReservationAt() != null;
    }

    public boolean completePickup(int reservationId) {
        reservationMapper.completePickup(reservationId);

        ReservationStatusDto dto = reservationMapper.findByReservationId(reservationId);

        return dto.getPickedUpAt() != null;
    }
}
