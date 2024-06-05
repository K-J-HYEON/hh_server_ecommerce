package hhplus.ecommerce.config;

import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.payment.Payment;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DataPlatformSendService {
    private final OkHttpClient client = new OkHttpClient();

    public void send(Order order, Payment payment) {
        String url = "https://mockapi.com";

        RequestBody body = RequestBody.create(
                order.toString() + payment.toString(),
                MediaType.get("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
        } catch (Exception e) {
//            log.error("주문 정보 전송 실패, {}", e.getMessage());
            System.out.println("주문 정보 전송 실패" + e.getMessage());
        }

    }
}