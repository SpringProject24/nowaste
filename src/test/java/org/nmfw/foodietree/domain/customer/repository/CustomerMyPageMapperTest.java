package org.nmfw.foodietree.domain.customer.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nmfw.foodietree.domain.customer.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
//@Transactional
class CustomerMyPageMapperTest {

    @Autowired
    CustomerMyPageMapper customerMyPageMapper;

    @Test
    @DisplayName("회원 개별 조회")
    void findOneTest() {
        //given
        String customerId = "test@gmail.com";
        //when
        Customer customer = customerMyPageMapper.findOne(customerId);
        //then
        assertEquals("김테스트", customer.getNickname());
    }
}