package org.nmfw.foodietree.domain.customer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.customer.dto.resp.CustomerMyPageDto;
import org.nmfw.foodietree.domain.customer.entity.Customer;
import org.nmfw.foodietree.domain.customer.mapper.CustomerMyPageMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerMyPageService {
    private final CustomerMyPageMapper customerMyPageMapper;

    // customer 마이페이지 소비자 정보 조회 중간 처리
    public CustomerMyPageDto customerInfo(String customerId, HttpServletRequest request, HttpServletResponse response) {
        CustomerMyPageDto customer = customerMyPageMapper.findOne(customerId);

        String currentCustomerId = customer.getCustomerId();

        CustomerMyPageDto responseDto = CustomerMyPageDto.builder()
                .customerId(customer.getCustomerId())
                .nickname(customer.getNickname())
                .profileImage(customer.getProfileImage())
                .customerPhoneNumber(customer.getCustomerPhoneNumber())
                .build();

        return responseDto;
    }

    // 마이페이지 중간처리

}
