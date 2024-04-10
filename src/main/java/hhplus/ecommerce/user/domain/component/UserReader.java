package hhplus.ecommerce.user.domain.component;

import hhplus.ecommerce.user.domain.User;
import hhplus.ecommerce.user.infrastructure.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserReader {
    private final UserRepository userRepository;

    public UserReader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User retrieveByUserId(Long userId) {
        return userRepository.findByUserId(userId);
    }
}