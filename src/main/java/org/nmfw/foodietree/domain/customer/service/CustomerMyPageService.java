package org.nmfw.foodietree.domain.customer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.customer.dto.resp.CustomerMyPageDto;
import org.nmfw.foodietree.domain.customer.entity.value.PreferredFoodCategory;
import org.nmfw.foodietree.domain.customer.mapper.CustomerMyPageMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerMyPageService {
    private final CustomerMyPageMapper customerMyPageMapper;

    // customer 마이페이지 소비자 정보 조회 중간 처리
    public CustomerMyPageDto customerInfo(String customerId, HttpServletRequest request, HttpServletResponse response) {

        CustomerMyPageDto customer = customerMyPageMapper.findOne(customerId);
        List<String> preferenceAreas = customerMyPageMapper.findPreferenceAreas(customerId);
        List<String> preferenceFoods = customerMyPageMapper.findPreferenceFoods(customerId);

        // preferenceFoods String 반환 된 값들 enum(PreferredFoodCategory)으로 변경
        List<PreferredFoodCategory> preferredFoodCategories = preferenceFoods.stream()
                .map(PreferredFoodCategory::fromKoreanName)
                .collect(Collectors.toList());

        return CustomerMyPageDto.builder()
                .customerId(customer.getCustomerId())
                .nickname(customer.getNickname())
                .profileImage(customer.getProfileImage())
                .customerPhoneNumber(customer.getCustomerPhoneNumber())
                .preferredFood(preferredFoodCategories)
                .preferredArea(preferenceAreas)
                .build();
    }
}