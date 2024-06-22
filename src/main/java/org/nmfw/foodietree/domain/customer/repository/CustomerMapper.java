package org.nmfw.foodietree.domain.customer.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nmfw.foodietree.domain.customer.entity.Customer;

@Mapper
public interface CustomerMapper {

    // 회원 가입
    boolean save(Customer customer);

    // 회원 정보 개별 조회
    Customer findOne(String account);

    // 중복 확인
    boolean existsById(@Param("customerId") String customerId);
}
