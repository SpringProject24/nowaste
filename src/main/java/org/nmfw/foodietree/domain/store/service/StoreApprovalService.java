package org.nmfw.foodietree.domain.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.store.entity.Store;
import org.nmfw.foodietree.domain.store.entity.value.StoreCategory;
import org.nmfw.foodietree.domain.store.mapper.StoreApprovalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreApprovalService {

    @Autowired
    private final StoreApprovalMapper storeApprovalMapper;

    public void registerStoreInfo(String storeId, String storeName, String address, StoreCategory category, String businessNumber, String storeLicenseNumber) {
        Store Store = storeApprovalMapper.selectStoreById(storeId);
        if (Store != null) {
            Store.setStoreName(storeName); // 상호명
            Store.setAddress(address); // 가게 주소
            Store.setCategory(category.getFoodType()); // 업종
            Store.setBusinessNumber(businessNumber); // 가게 번호
            Store.setStoreLicenseNumber(storeLicenseNumber); // 사업자등록번호
            storeApprovalMapper.updateStoreInfo(Store);
        }
    }
}