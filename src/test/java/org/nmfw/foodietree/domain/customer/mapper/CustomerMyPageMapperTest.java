package org.nmfw.foodietree.domain.customer.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nmfw.foodietree.domain.customer.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
//@Transactional
class CustomerMyPageMapperTest {

    private static final Logger log = LoggerFactory.getLogger(CustomerMyPageMapperTest.class);
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

    @Test
    @DisplayName("회원 개인정보 업데이트")
    void updateCustomerInfoTest() {
        //given
        String customerId = "test@gmail.com";
        String type = "nickname";
        String value = "나는야김테스트";
        //when
        customerMyPageMapper.updateCustomerInfo(customerId, type, value);
        Customer customer = customerMyPageMapper.findOne(customerId);
        //then
        assertEquals(value, customer.getNickname());
    }
}