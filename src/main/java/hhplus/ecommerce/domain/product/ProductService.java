package hhplus.ecommerce.domain.product;

import java.util.List;

public interface ProductService {

    List<Product> readProductInfo();

    Product readProductInfoDetail(Long productId);
    List<Product> readPopularProduct();
}
