package org.nmfw.foodietree.domain.customer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.customer.dto.request.SignUpDto;
import org.nmfw.foodietree.domain.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    //회원가입 양식 열기
    @GetMapping("/sign-up")
    public void signUp() {
        log.info("customer/sign-up GET : forwarding to sign-up.jsp");
        //return "customer/sign-up";
    }

    //회원가입 요청 처리
    @PostMapping("/sign-up")
    public String signUp(SignUpDto dto) {
        log.info("/customer/sing-up POST ");
        log.debug("parameter: {}", dto);

        boolean flag = customerService.join(dto);

        return flag ? "redirect:" : "redirect:/customers/sign-up";
        //회원가입 성공시 가게 주소 입력하는 소비자 메인창으로 이동
        //
    }



    /*
    //로그인 양식 열기(조회)
    @GetMapping("/sign-in")
    public void signIn() {
        log.info("customer/sign-in GET : forwarding to sign-in.jsp");
    }

    //로그인 요청 처리
    @PostMapping("/sign-in")
    public String signIn(CustomerLoginDto dto) {
        log.info("/customer/sign-in POST");
        log.debug("parameter: {}", dto);
        return "redirect:/index";
    }
    */
}
