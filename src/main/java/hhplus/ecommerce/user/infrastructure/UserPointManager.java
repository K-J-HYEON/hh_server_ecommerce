package hhplus.ecommerce.user.infrastructure;

import hhplus.ecommerce.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserPointManager {
    private final UserRepository userRepository;

    public UserPointManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User chargePoint(User user, Long chargingPoint) {
        return userRepository.updateUserPoint(user.addPoint(chargingPoint));
    }
}
