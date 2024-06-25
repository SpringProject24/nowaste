package org.nmfw.foodietree.domain.store.dto.resp;

import lombok.*;
import org.nmfw.foodietree.domain.store.entity.Store;
import org.nmfw.foodietree.domain.store.entity.value.StoreCategory;

@Setter @Getter @ToString
@EqualsAndHashCode
@AllArgsConstructor
public class StoreApprovalDto {

    private String storeId;
    private String storeName;
    private String address;
    private StoreCategory category;
    private String businessNumber;
    private String storeLicenseNumber;

    public Store toEntity() {

        Store s = new Store();
        s.setStoreId(this.getStoreId());
        s.setStoreName(this.getStoreName());
        s.setAddress(this.getAddress());
        s.setCategory(this.getCategory().getFoodType());
        s.setBusinessNumber(this.getBusinessNumber());
        s.setStoreLicenseNumber(this.getStoreLicenseNumber());
        return s;
    }
}