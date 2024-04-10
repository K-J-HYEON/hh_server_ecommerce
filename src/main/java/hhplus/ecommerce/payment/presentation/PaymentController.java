package hhplus.ecommerce.payment.presentation;


import hhplus.ecommerce.payment.application.PaymentService;
import hhplus.ecommerce.payment.domain.Payment;
import hhplus.ecommerce.payment.dto.request.PaymentReq;
import hhplus.ecommerce.payment.dto.response.PaymentRes;
import hhplus.ecommerce.product.dto.res.ProductInfoRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@Tag(name = "상품 결제", description = "상품 결제 관련 api 입니다.")
@RestController
@RequestMapping("/ecommerce/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @Operation(summary = "상품 결제 메서드", description = "상품 결제 메서드입니다.", tags = "결제")	// (1)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ProductInfoRes.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ProductInfoRes.class)))
    })
    @PostMapping("/{paymentId}/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentRes pay(@PathVariable Long userId,
                          @Valid @RequestBody PaymentReq req) {
        Payment payment = paymentService.pay(userId, req);
        return PaymentRes.from(payment);
    }
}
