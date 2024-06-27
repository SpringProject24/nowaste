package org.nmfw.foodietree.domain.reservation.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nmfw.foodietree.domain.reservation.dto.resp.ReservationDetailDto;

import java.util.List;

@Mapper
public interface ReservationMapper {

    // 예약 조회
    List<ReservationDetailDto> findAll(@Param("customerId") String customerId);

    // 예약 상세 조회
    ReservationDetailDto findById(@Param("customerId") String customerId,
                                  @Param("reservationId") String reservationId);

    // 예약 취소
    boolean cancelReservation(@Param("customerId") String customerId,
                              @Param("reservationId") String reservationId);

    // 픽업 완료
    boolean completePickup(@Param("customerId") String customerId,
                           @Param("reservationId") String reservationId);
}
