package hhplus.ecommerce.api;

import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.order.event.OrderPaidEvent;
import hhplus.ecommerce.domain.payment.Payment;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventHandler {
    private final OkHttpClient client = new OkHttpClient();

    @EventListener
    public void orderEventHandler(OrderPaidEvent event) {
        String url = "https://mockapi.com";

        Order order = event.order();
        Payment payment = event.payment();

        RequestBody body = RequestBody.create(
                order.toString() + payment.toString(),
                MediaType.get("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

//        try {
//            client.newCall(request).execute();
//        } catch (Exception e) {
//            log.error("API 요청 실패, {}", e.getMessage());
//        }
    }
}