package hhplus.ecommerce.domain.user;

import org.springframework.stereotype.Component;

@Component
public class UserReader {
    private final UserRepository userRepository;

    public UserReader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User readById(Long userId) {
        return userRepository.findById(userId);
    }

    public User readByIdWithLock(Long userId) {
        return userRepository.findByIdWithLock(userId);
    }
}
