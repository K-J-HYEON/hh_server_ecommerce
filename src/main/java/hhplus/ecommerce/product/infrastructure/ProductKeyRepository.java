package hhplus.ecommerce.product.infrastructure;

import hhplus.ecommerce.product.domain.Product;
import hhplus.ecommerce.product.entity.ProductEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.Comparator;
import java.util.List;

@Repository
public class ProductKeyRepository implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    public ProductKeyRepository(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll().stream()
                .sorted(Comparator.comparing(ProductEntity::getCreateAt).reversed())
                .map(ProductEntity::toProduct)
                .toList();
    }

    @Override
    public Product findById(Long productId) {
        return productJpaRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("상품 정보를 찾지 못했습니다. - id: " + productId))
                .toProduct();
    }
}