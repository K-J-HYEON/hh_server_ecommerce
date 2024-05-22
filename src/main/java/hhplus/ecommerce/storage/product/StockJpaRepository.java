package hhplus.ecommerce.storage.product;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import java.util.Optional;

public interface StockJpaRepository extends JpaRepository<StockEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<StockEntity> findByProductId(Long productId);
}