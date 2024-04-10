package hhplus.ecommerce.user.application;

import hhplus.ecommerce.user.domain.User;
import hhplus.ecommerce.user.util.UserRetrieve;
import hhplus.ecommerce.user.infrastructure.UserPointManager;
import org.springframework.stereotype.Service;

@Service
public class UserPointServiceImpl implements UserPointService {
    private final UserRetrieve userRetrieve;
    private final UserPointManager userPointManager;

    public UserPointServiceImpl(UserRetrieve userRetrieve, UserPointManager userPointManager) {
        this.userRetrieve = userRetrieve;
        this.userPointManager = userPointManager;
    }

    @Override
    public Long chargePoint(Long userId, Long amount) {
        User user = userRetrieve.readByUserId(userId);
        User chargedUser = userPointManager.chargePoint(user, amount);
        return chargedUser.point();
    }

    @Override
    public Long readPoint(Long userId) {
        User user = userRetrieve.readByUserId(userId);
        return user.point();
    }
}
