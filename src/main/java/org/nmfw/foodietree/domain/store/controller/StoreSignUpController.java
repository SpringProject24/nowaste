package org.nmfw.foodietree.domain.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.store.dto.request.StoreSignUpDto;
import org.nmfw.foodietree.domain.store.service.StoreSignUpService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class StoreSignUpController {
    private final StoreSignUpService storeSignUpService;

    /**
     *
     * @param dto
     * @return StoreSignUpService에서 성공적으로 회원가입완료시 다음페이지로 이동
     */
    @PostMapping("/store-sign-up")
    public String StoreSignUp(@Validated StoreSignUpDto dto) {
        log.info("/store-sign-up POST");
        log.debug("parameter:{}", dto);

        boolean flag = storeSignUpService.storeSignUp(dto);

        return " ";
    }
}
