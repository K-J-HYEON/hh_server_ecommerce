package hhplus.ecommerce.domain.user;


public interface UserRepository {
    User findByUserId(Long userId);

    User updateUserPoint(User user);
}