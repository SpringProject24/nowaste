package org.nmfw.foodietree.domain.reservation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.customer.dto.resp.MyPageReservationDetailDto;
import org.nmfw.foodietree.domain.customer.service.CustomerMyPageService;
import org.nmfw.foodietree.domain.reservation.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    private final CustomerMyPageService customerMyPageService;

    @GetMapping("/{customerId}")
    public ResponseEntity<List<MyPageReservationDetailDto>> getReservationList(@PathVariable String customerId) {
        List<MyPageReservationDetailDto> reservations = customerMyPageService.getReservationInfo(customerId);
        return ResponseEntity.ok().body(reservations);
    }

    @PatchMapping("/{reservationId}/cancel")
    public ResponseEntity<?> cancelReservation(@PathVariable int reservationId) {
        log.info("cancel reservation");
        boolean flag = reservationService.cancelReservation(reservationId);

        return flag ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{reservationId}/pickup")
    public ResponseEntity<?> completePickup(@PathVariable int reservationId) {
        log.info("complete pickup");
        boolean flag = reservationService.completePickup(reservationId);

        return flag ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
