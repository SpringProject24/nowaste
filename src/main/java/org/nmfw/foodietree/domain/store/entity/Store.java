package org.nmfw.foodietree.domain.store.entity;

import lombok.*;
import org.nmfw.foodietree.domain.store.entity.value.StoreApproveStatus;
import org.nmfw.foodietree.domain.store.entity.value.StoreCategory;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {
    private String storeId;
    private String password;
    private StoreCategory category;
    private String address;
    private StoreApproveStatus approve;
    private int warningCount;
    private int price;
    private String businessNumber;
    private String storeImage;
}
