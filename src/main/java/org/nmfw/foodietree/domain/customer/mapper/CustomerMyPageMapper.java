package org.nmfw.foodietree.domain.customer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nmfw.foodietree.domain.customer.entity.Customer;

@Mapper
public interface CustomerMyPageMapper {

    // 회원 정보 개별조회
    Customer findOne(String customerId);

    /**
     * 회원정보 업데이트
     * @param customerId : 업데이트할 회원 아이디
     * @param type : 업데이트할 필드
     *             (customer_password, nickname, customer_phone_number, profile_image)
     * @param value : 해당 필드에 새로 지정할 값
     */
    void updateCustomerInfo(
            @Param("customerId") String customerId,
            @Param("type") String type,
            @Param("value") String value
    );
}
