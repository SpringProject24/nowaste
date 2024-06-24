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

    //고객 정보를 데이터베이스에 저장하거나 조회하는데 사용되는 객체
    private final CustomerMapper customerMapper;
    //비밀번호 암호화 객체
    private final PasswordEncoder encoder;

    public boolean join(SignUpDto dto) {
        String id = dto.getCustomerId();
        String password = dto.getCustomerPassword();

        // 아이디 형식: 영문자(대소문자 구분 없음), 소문자로 구성,
        // 길이는 5~20 사이, 특수문자 불가
        if(!id.matches("^[a-zA-Z0-9]{5,20}$")) {
            throw new IllegalArgumentException("아이디 형식이 틀렸습니다.");
        }
        // 비밀번호 형식: 길이가 최대 8글자 이면서, 영문자, 숫자 포함
        if(!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{1,8}$")) {
            throw new IllegalArgumentException("비밀번호 형식이 틀렸습니다.");
        }

        // ??????계정인지 아이디인지 구분 필요할 듯함.
        if(!customerMapper.existsById(dto.getCustomerId())) {
            throw new IllegalArgumentException("존재하지 않는 아이디입니다.");
        }

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

