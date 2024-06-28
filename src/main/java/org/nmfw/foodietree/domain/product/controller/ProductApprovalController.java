package org.nmfw.foodietree.domain.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.product.Service.ProductApprovalService;
import org.nmfw.foodietree.domain.product.Util.FileUtil;
import org.nmfw.foodietree.domain.product.dto.response.ProductApprovalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/storeMyPage")
@Slf4j
@RequiredArgsConstructor
public class ProductApprovalController {

    @Value("${file.upload.root-path}")
    private String rootPath;

    @Autowired
    private final ProductApprovalService productApprovalService;

    @GetMapping("/productApproval-form")
    public String newProductApproval(HttpSession session) {

        // 세션에서 로그인된 사용자 ID 가져오기
//        String storeId = (String) session.getAttribute("storeId");

        return "Product/product-form";
    }

    @PostMapping("/productApproval-result")
    public String approveProduct(@Validated ProductApprovalDto productDto, Model model, HttpSession session) {

        // 세션에서 로그인된 사용자 ID 가져오기
        String storeId = (String) session.getAttribute("storeId");
        if (storeId == null) {
            storeId = "hoho@bbb.com"; // 임시 값 설정, 실제로는 세션에서 가져온 값을 사용
        }

        // storeId를 DTO에 설정
        productDto.setStoreId(storeId);

        model.addAttribute("productDto", productDto);

        // 프로필 사진 추출
        MultipartFile proImage = productDto.getProImage();

        String profilePath = null;
        if (!proImage.isEmpty()) {
            // 서버에 업로드 후 업로드 경로 반환
            profilePath = FileUtil.uploadFile(rootPath, proImage);
        }

        boolean flag1 = productApprovalService.productColumnApproval(productDto, profilePath);

        boolean flag2 = productApprovalService.storeColumnApproval(productDto);

        if(flag1){
            if(flag2){
                return "Product/product-list";
            }
            return "error";
        }
        return "error";


    }
}
