package hhplus.ecommerce.domain.user;

import hhplus.ecommerce.common.LockHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserPointService {
    private final UserReader userReader;
    private final UserPointManager userPointManager;
    private final LockHandler lockhandler;

    private static final String USER_POINT_LOCK_PREFIX = "USER_";

    public UserPointService(UserReader userReader, UserPointManager userPointManager, LockHandler lockhandler) {
        this.userReader = userReader;
        this.userPointManager = userPointManager;
        this.lockhandler = lockhandler;
    }

    @Transactional
    public Long chargePoint(Long userId, Long amount) {
        String key = USER_POINT_LOCK_PREFIX + userId;
        lockhandler.lock(key, 2, 1);
        User chargedUser;
        try {
            chargedUser = userPointManager.chargePoint(userId, amount);
        } finally {
            lockhandler.unlock(key);
        }
        return chargedUser.point();

    }

    public Long readPoint(Long userId) {
        User user = userReader.readById(userId);
        return user.point();
    }
}
