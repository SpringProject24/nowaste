package org.nmfw.foodietree.domain.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.customer.service.CustomerMyPageService;
import org.nmfw.foodietree.domain.product.dto.response.ProductDto;
import org.nmfw.foodietree.domain.product.mapper.ProductMainPageMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductMainPageService {
    private final ProductMainPageMapper productMainPageMapper;
    private final CustomerMyPageService customerMyPageService;

    // product 메인페이지 상품정보 조회 중간 처리
    public List<ProductDto> getProductInfo() {
        return productMainPageMapper.findAll();
    }



}
