package hhplus.ecommerce.user.infrastructure;

import hhplus.ecommerce.user.domain.User;


public interface UserRepository {
    User findByUserId(Long userId);

    User updateUserPoint(User user);
}