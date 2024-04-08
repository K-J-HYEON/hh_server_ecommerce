package hhplus.ecommerce.user.infrastructure;

import hhplus.ecommerce.user.domain.User;
import hhplus.ecommerce.user.entity.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UserKeyRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserKeyRepository(UserJpaRepository userJpaRepository) {
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
        UserEntity userEntity = userJpaRepository.findById(user.id())
                .orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다. - id: " + user.id()));
        userEntity.updatePoint(user);
        return userJpaRepository.save(userEntity).toUser();
    }
}