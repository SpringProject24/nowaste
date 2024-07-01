package org.nmfw.foodietree.domain.customer.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nmfw.foodietree.domain.customer.dto.resp.MyPageReservationDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class CustomerMyPageServiceTest {
    @Autowired
    CustomerMyPageService customerMyPageService;

    @Test
    @DisplayName("reservation test")
    void reservationTest() {
        //given
        String customerId = "test@gmail.com";
        //when
        List<MyPageReservationDetailDto> reservationInfo = customerMyPageService.getReservationInfo(customerId);
        //then
        for (MyPageReservationDetailDto myPageReservationDetailDto : reservationInfo) {
            System.out.println("\n\n\n");
            System.out.println("myPageReservationDetailDto = " + myPageReservationDetailDto.getStatus());
            System.out.println("myPageReservationDetailDto = " + myPageReservationDetailDto.getStoreName());
            System.out.println("\n\n\n");
        }
    }
}