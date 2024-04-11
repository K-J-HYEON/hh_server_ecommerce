package hhplus.ecommerce.storage.user;

import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UserCoreRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserCoreRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User findByUserId(Long userId) {
        return userJpaRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다. - id: " + userId))
                .toUser();
    }

    @Override
    public User updateUserPoint(User user) {
        UserEntity userEntity = userJpaRepository.findById(user.userId())
                .orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다. - id: " + user.userId()));
        userEntity.updatePoint(user);
        return userJpaRepository.save(userEntity).toUser();
    }
}