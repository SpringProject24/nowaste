package org.nmfw.foodietree.domain.customer.repository;

import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nmfw.foodietree.domain.customer.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerMapperTest {

    @Autowired
    CustomerMapper customerMapper;

    @Test
    @DisplayName("회원가입에 성공해야 한다")
    void saveTest() {
        //given
        Customer customer = Customer.builder()
                .customerId("apple")
                .customerPassword("apple123!")
                .nickName("아오리사과")
                .customerPhoneNumber("0104445555")
                .build();

        //when
        boolean flag = customerMapper.save(customer);

        //then
        assertTrue(flag);

    }

    @Test
    @DisplayName("아이디가 apple인 회원의 중복확인 결과가 ture이다.")
    void findOneTest() {
        //given
        String id = "apple";
        //when
        Customer foundCustomer = customerMapper.findOne(id);
        //then
        assertEquals("아오리사과", foundCustomer.getNickName());
    }
    
    @Test
    @DisplayName("계정명이 apple인 회원은 중복확인 결과가 true이다.")
    void existsTest() {
        //given
        String id = "apple";
        //when
        boolean flag = customerMapper.existsById(id);
        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("아이디가 day6인 회원은 중복확인 결과가 false이다.")
    void existsTest2() {
        String customerId = "day6"; // 테스트할 customerId
        boolean exists = customerMapper.existsById(customerId);
        assertTrue(exists); // 고객이 존재하는지 여부를 검증
    }

}