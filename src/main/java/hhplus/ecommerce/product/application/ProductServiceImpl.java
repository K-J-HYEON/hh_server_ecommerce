package hhplus.ecommerce.product.application;

import hhplus.ecommerce.product.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRetrieve productRetrieve;

    public ProductServiceImpl(ProductRetrieve productRetrieve) {
        this.productRetrieve = productRetrieve;
    }

    @Override
    public List<Product> readProductInfo() {
        return productRetrieve.readAll();
    }

    @Override
    public Product readProductInfoDetail(Long productId) {
        return productRetrieve.readById(productId);
    }
}
