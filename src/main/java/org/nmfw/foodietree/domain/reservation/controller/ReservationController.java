package org.nmfw.foodietree.domain.reservation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.customer.dto.resp.MyPageReservationDetailDto;
import org.nmfw.foodietree.domain.customer.service.CustomerMyPageService;
import org.nmfw.foodietree.domain.reservation.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
