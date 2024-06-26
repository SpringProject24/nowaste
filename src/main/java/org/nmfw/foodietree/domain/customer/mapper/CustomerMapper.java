package org.nmfw.foodietree.domain.customer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nmfw.foodietree.domain.customer.entity.Customer;

import java.util.List;

@Mapper
public interface CustomerMapper {

    // 회원 가입
    boolean save(Customer customer);

    // 회원 정보 개별 조회
    Customer findOne(String customer);

    /**
     * @param customerId - 이메일
     * @return - 중복이면 true, 아니면 false
     */
    // 중복 확인
    boolean existsById(@Param("customerId") String customerId);

}
