package org.nmfw.foodietree.domain.store.entity;

import lombok.*;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginIdCheck {

    private String storeId; // 아이디(계정) = 이메일
    private String password; // 비밀번호
    private String nickname; // 닉네임
    private String customerPhoneNumber; // 휴대폰 번호
    private String profileImage; // 프로필 사진
}
