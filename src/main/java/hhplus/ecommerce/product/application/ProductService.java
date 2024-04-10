package hhplus.ecommerce.product.application;

import hhplus.ecommerce.product.domain.Product;
import java.util.List;

public interface ProductService {

    List<Product> readProductInfo();

    Product readProductInfoDetail(Long productId);
    List<Product> readPopularProduct();
}
