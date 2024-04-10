package hhplus.ecommerce.user.application;

import hhplus.ecommerce.user.domain.User;
import hhplus.ecommerce.user.domain.component.UserReader;
import hhplus.ecommerce.user.infrastructure.UserPointManager;
import org.springframework.stereotype.Service;

@Service
public class UserPointServiceImpl implements UserPointService {
    private final UserReader userReader;
    private final UserPointManager userPointManager;

    public UserPointServiceImpl(UserReader userReader, UserPointManager userPointManager) {
        this.userReader = userReader;
        this.userPointManager = userPointManager;
    }

    @Override
    public Long chargePoint(Long userId, Long amount) {
        User user = userReader.retrieveByUserId(userId);
        User chargedUser = userPointManager.chargePoint(user, amount);
        return chargedUser.point();
    }

    @Override
    public Long readPoint(Long userId) {
        User user = userReader.retrieveByUserId(userId);
        return user.point();
    }
}
