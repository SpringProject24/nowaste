package org.nmfw.foodietree.domain.customer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.customer.dto.request.SignUpDto;
import org.nmfw.foodietree.domain.customer.entity.Customer;
import org.nmfw.foodietree.domain.customer.repository.CustomerMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final PasswordEncoder encoder;

    public boolean join(SignUpDto dto) {
        // dto를 엔터티로 변환
        Customer customer = dto.toEntity();

        // 비밀번호 인코딩
        String encodedPassword = encoder.encode(dto.getCustomerPassword());
        customer.setCustomerPassword(encodedPassword);

        return customerMapper.save(customer);
    }

    //아이디(이메일) 중복 검사
    public boolean checkIdentifier(String type) {
        return customerMapper.existsById(type);
    }
}

