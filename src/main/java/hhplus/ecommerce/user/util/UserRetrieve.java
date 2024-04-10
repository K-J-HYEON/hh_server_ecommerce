package hhplus.ecommerce.user.util;

import hhplus.ecommerce.user.domain.User;
import hhplus.ecommerce.user.infrastructure.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserRetrieve {
    private final UserRepository userRepository;

    public UserRetrieve(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User retrieveByUserId(Long userId) {
        return userRepository.findByUserId(userId);
    }
}