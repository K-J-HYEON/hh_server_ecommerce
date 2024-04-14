package hhplus.ecommerce.domain.user;

import org.springframework.stereotype.Service;

@Service
public class UserPointService {
    private final UserReader userReader;
    private final UserPointManager userPointManager;

    public UserPointService(UserReader userReader, UserPointManager userPointManager) {
        this.userReader = userReader;
        this.userPointManager = userPointManager;
    }

    public Long chargePoint(Long userId, Long amount) {
        User user = userReader.retrieveByUserId(userId);
        User chargedUser = userPointManager.chargePoint(user, amount);
        return chargedUser.point();
    }

    public Long readPoint(Long userId) {
        User user = userReader.retrieveByUserId(userId);
        return user.point();
    }
}
