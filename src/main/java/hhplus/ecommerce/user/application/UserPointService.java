package hhplus.ecommerce.user.application;

public interface UserPointService {
    Long chargePoint(Long userId, Long amount);

    Long readPoint(Long userId);
}