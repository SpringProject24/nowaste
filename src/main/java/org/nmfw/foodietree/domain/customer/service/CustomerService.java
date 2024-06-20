package org.nmfw.foodietree.domain.customer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.customer.dto.request.SignUpDto;
import org.nmfw.foodietree.domain.customer.entity.Customer;
import org.nmfw.foodietree.domain.customer.repository.CustomerMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerMapper customerMapper;

    public boolean join(SignUpDto dto) {
        //dto를 엔터티로 변환
        Customer customer = dto.toEntity();

        //비밀번호 인토딩
        customerMapper.save(customer);
        return false;
    }
}
