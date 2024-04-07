package hhplus.ecommerce.product.presentation;

import hhplus.ecommerce.product.application.ProductService;
import hhplus.ecommerce.product.domain.Product;
import hhplus.ecommerce.product.dto.res.ProductInfoDetailRes;
import hhplus.ecommerce.product.dto.res.ProductInfoRes;
import hhplus.ecommerce.product.dto.res.ProductListRes;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ecommerce/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ProductListRes readProductInfo() {
        List<Product> products = productService.readProductInfo();

        List<ProductInfoRes> productInfoResList = products.stream()
                .map(ProductInfoRes::from)
                .toList();
        return new ProductListRes(productInfoResList);
    }

    @GetMapping("/{productId}")
    public ProductInfoDetailRes readProductInfoDetail(@PathVariable Long productId) {
        Product product = productService.readProductInfoDetail(productId);
        return ProductInfoDetailRes.from(product);
    }
}
