package hhplus.ecommerce.domain.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserReader userReader;

    public UserService(UserReader userReader) {
        this.userReader = userReader;
    }

    public User getUser(Long userId) {
        return userReader.retrieveByUserId(userId);
    }
}
