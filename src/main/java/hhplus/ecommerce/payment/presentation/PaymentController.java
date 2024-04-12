package hhplus.ecommerce.payment.presentation;


import hhplus.ecommerce.payment.application.PaymentService;
import hhplus.ecommerce.payment.domain.Payment;
import hhplus.ecommerce.payment.presentation.dto.request.PaymentReq;
import hhplus.ecommerce.payment.presentation.dto.response.PaymentRes;
import hhplus.ecommerce.product.presentation.dto.res.ProductInfoRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ecommerce/api/order")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @Tag(name = "상품 주문 및 결제 API", description = "상품주문 & 결제 API입니다.")
    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentRes pay(@PathVariable Long userId,
                          @Valid @RequestBody PaymentReq req) {
        Payment payment = paymentService.pay(userId, req);
        return PaymentRes.from(payment);
    }
}
