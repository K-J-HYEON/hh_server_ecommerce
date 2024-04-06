package hhplus.ecommerce.user.infrastructure;

import hhplus.ecommerce.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository {
    Optional<User> findByUserId(Long userId);
}
