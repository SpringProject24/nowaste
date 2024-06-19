package org.nmfw.foodietree.domain.customer.repository;

import org.apache.ibatis.annotations.Mapper;
import org.nmfw.foodietree.domain.customer.entity.Customer;

@Mapper
public interface CustomerMyPageMapper {

    // 회원 정보 개별조회
    Customer findOne(String customerId);


}
