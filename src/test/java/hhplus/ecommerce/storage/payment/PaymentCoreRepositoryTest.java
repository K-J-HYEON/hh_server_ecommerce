package hhplus.ecommerce.storage.payment;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;

class PaymentCoreRepositoryTest {

    @MockBean
    private PaymentJpaRepository paymentJpaRepository;

    @InjectMocks
    private PaymentCoreRepository paymentCoreRepository;

//    @BeforeEach
//    void setUp() {
//        paymentJpaRepository = mock(PaymentJpaRepository.class);
//        paymentCoreRepository = new PaymentCoreRepository(paymentJpaRepository);
//    }

    @Test
    @DisplayName("결제 정보를 찾지 못하였을 경우 에러 발생")
    void payment_not_found_then_error() {
        // Given
        Long paymentId = 100L;

        // When && Then
        assertThrows(EntityNotFoundException.class, () -> {
            paymentCoreRepository.findById(paymentId);
        });
    }
}