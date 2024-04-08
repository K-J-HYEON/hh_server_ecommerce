package hhplus.ecommerce.product.infrastructure;

import hhplus.ecommerce.product.domain.Product;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository {

    List<Product> findAll();

    Product findById(Long productId);
}
