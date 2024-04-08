package hhplus.ecommerce.user.infrastructure;

import hhplus.ecommerce.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserReader {
    private final UserRepository userRepository;

    public UserReader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User readByUserId(Long userId) {
        return userRepository.findByUserId(userId);
    }
}