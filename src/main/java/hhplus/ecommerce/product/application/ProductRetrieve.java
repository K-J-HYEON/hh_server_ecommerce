package hhplus.ecommerce.product.application;


import hhplus.ecommerce.product.domain.Product;
import hhplus.ecommerce.product.infrastructure.ProductRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductRetrieve {
    private final ProductRepository productRepository;

    public ProductRetrieve(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> readAll() {
        return productRepository.findAll();
    }

    public Product readById(Long produtId) {
        return productRepository.findById(produtId);
    }
}
