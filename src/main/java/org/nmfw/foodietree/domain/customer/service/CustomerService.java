package org.nmfw.foodietree.domain.customer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.customer.dto.request.CustomerLoginDto;
import org.nmfw.foodietree.domain.customer.dto.request.SignUpDto;
import org.nmfw.foodietree.domain.customer.dto.resp.LoginUserInfoDto;
import org.nmfw.foodietree.domain.customer.entity.Customer;
import org.nmfw.foodietree.domain.customer.mapper.CustomerMapper;
import org.nmfw.foodietree.domain.store.service.StoreLoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.nmfw.foodietree.domain.store.service.StoreLoginResult.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

	//고객 정보를 데이터베이스에 저장하거나 조회하는데 사용되는 객체
	private final CustomerMapper customerMapper;
	//비밀번호 암호화 객체
	private final PasswordEncoder encoder;

	//회원 가입 중간 처리 (저장 성공 여부 boolean 값으로 반환)
	public boolean join(SignUpDto dto) {

		Customer customer = dto.toEntity();

		// 비밀번호를 인코딩(암호화)
		String encodedPassword = encoder.encode(dto.getCustomerPassword());
		customer.setCustomerPassword(encodedPassword); //인코딩 된 비밀번호를 Customer에 주입

		return customerMapper.save(customer); //데이터에 저장
	}

//	// 아이디 형식: 영문자(대소문자 구분 없음), 소문자로 구성,
//	// 길이는 5~20 사이, 특수문자 불가
//        if(!id.matches("^[a-zA-Z0-9]{5,20}$")) {
//            throw new IllegalArgumentException("아이디 형식이 틀렸습니다.");
//        }
//        // 비밀번호 형식: 길이가 최대 8글자 이면서, 영문자, 숫자 포함
//        if(!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{1,8}$")) {
//            throw new IllegalArgumentException("비밀번호 형식이 틀렸습니다.");
//        }

//        if(!customerMapper.existsById(dto.getCustomerId())) {
//            throw new IllegalArgumentException("존재하지 않는 아이디입니다.");
//        }


	//로그인 검증 처리
	public LoginResult authenticate(CustomerLoginDto dto, HttpSession session) {

		// 회원가입 여부 확인
		String customerId = dto.getCustomerId();
		Customer foundCustomer = customerMapper.findOne(customerId); //db에 있는 customerId 꺼내옴.

		if (foundCustomer == null) {
			log.info("{} - 회원가입이 필요합니다.", customerId);
			return LoginResult.NO_ID;
		}

		// 비밀번호 일치 검사
		String inputPassword = dto.getCustomerPassword();
		String originPassword = foundCustomer.getCustomerPassword();

		if (!encoder.matches(inputPassword, originPassword)) {
			log.info("비밀번호가 일치하지 않습니다.");
			return LoginResult.NO_PW;
		}

		log.info("{}님 로그인 성공", foundCustomer.getNickName());


		//세션 최대 비활성화 간격
		int maxInactiveInterval = session.getMaxInactiveInterval();

		//세션 수명 1시간 설정
		session.setMaxInactiveInterval(60 * 60);
		log.debug("session time: {}", maxInactiveInterval);

		session.setAttribute("login", new LoginUserInfoDto(foundCustomer) );

		return LoginResult.SUCCESS;
	}

	// 아이디 중복 검사
	public boolean checkIdentifier(String keyword) {
		return customerMapper.existsById(keyword);
	}

}


