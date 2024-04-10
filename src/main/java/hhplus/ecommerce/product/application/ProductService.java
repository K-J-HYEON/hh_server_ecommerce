package hhplus.ecommerce.product.application;

import hhplus.ecommerce.config.BaseException;
import hhplus.ecommerce.product.domain.Product;
import hhplus.ecommerce.product.dto.res.ProductInfoRes;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> readProductInfo();

    Product readProductInfoDetail(Long productId);
    List<Product> readPopularProduct();
}
