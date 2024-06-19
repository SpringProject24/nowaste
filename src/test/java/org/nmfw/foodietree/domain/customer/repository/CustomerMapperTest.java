package org.nmfw.foodietree.domain.customer.repository;

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

}