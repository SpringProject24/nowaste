package org.nmfw.foodietree.domain.customer.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nmfw.foodietree.domain.customer.dto.request.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest @Transactional
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Test
    @DisplayName("회원가입 시 비밀번호가 암호화된다.")
    void joinTest() {
        //given
        SignUpDto dto = SignUpDto.builder()
                .customerId("dog@naver.com")
                .customerPassword("dog123!")
//                .nickName("오레오")
                .customerPhoneNumber("010-9876-5432")
                .build();
        //whenGIT
        boolean flag = customerService.join(dto);

        //then
        assertTrue(flag);
    }
}