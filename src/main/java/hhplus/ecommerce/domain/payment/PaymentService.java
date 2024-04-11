package hhplus.ecommerce.domain.payment;

import hhplus.ecommerce.api.dto.request.PaymentReq;

public interface PaymentService {
    Payment pay(Long userId, PaymentReq req);
}
