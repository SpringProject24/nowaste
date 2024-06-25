package org.nmfw.foodietree.domain.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.store.dto.resp.StoreApprovalDto;
import org.nmfw.foodietree.domain.store.entity.Store;
import org.nmfw.foodietree.domain.store.entity.value.StoreCategory;
import org.nmfw.foodietree.domain.store.service.StoreApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/storeMyPage")
@Slf4j
@RequiredArgsConstructor
public class StoreApprovalController {

    @Autowired
    private final StoreApprovalService StoreApprovalService;

    private StoreApprovalDto storeApprovalDto;


    @GetMapping("/storeApproval-form")
    public String storeApproval(HttpSession session){

        // 세션에서 로그인된 사용자 ID 가져오기
        String storeId =(String) session.getAttribute("storeId");


        return "StoreApproval/StoreApproval-form";
    }

    @PostMapping("/storeApproval-result")
    public String storeApprovalResult(@RequestParam("storeId") String storeId,
                                      @RequestParam("storeName") String storeName,
                                      @RequestParam("address") String address,
                                      @RequestParam("category") StoreCategory category,
                                      @RequestParam("businessNumber") String businessNumber,
                                      @RequestParam("storeLicenseNumber") String storeLicenseNumber,
                                      HttpSession session,
                                      Model model){




        // 서비스 호출하여 데이터베이스에 저장
        StoreApprovalService.registerStoreInfo(storeId, storeName, address, category, businessNumber, storeLicenseNumber);

        // 결과 페이지로 이동
        model.addAttribute("message", "승인 완료..");
        return "StoreApproval/StoreApproval-Result";

    }
}