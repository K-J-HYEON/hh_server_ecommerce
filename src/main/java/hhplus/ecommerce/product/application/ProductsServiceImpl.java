package hhplus.ecommerce.product.application;


import hhplus.ecommerce.config.BaseException;
import hhplus.ecommerce.product.domain.Product;
import hhplus.ecommerce.product.dto.res.ProductInfoRes;
import hhplus.ecommerce.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static hhplus.ecommerce.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductsServiceImpl implements ProductsService {

    private final ProductRepository productRepository;

    public ProductsServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductInfoRes userReadProductInfo(Long productId, Long userId) throws BaseException {

        Product product;
        try {
            product = (Product) productRepository.getById(productId);
        } catch (Exception exception) {
            throw new BaseException(PRODUCT_READ_FAILED);
        }
        return null;
    }

}
