package org.nmfw.foodietree.domain.customer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.customer.dto.request.CustomerLoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {

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
}

