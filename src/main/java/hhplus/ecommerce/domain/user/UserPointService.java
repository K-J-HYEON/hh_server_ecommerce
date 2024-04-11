package hhplus.ecommerce.domain.user;

public interface UserPointService {
    Long chargePoint(Long userId, Long amount);

    Long readPoint(Long userId);
}