package hhplus.ecommerce.api.dto;

import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.payment.Payment;

public record OrderEventForStatistics(Order order, Payment payment)  {
}
