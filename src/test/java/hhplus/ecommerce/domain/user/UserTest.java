package hhplus.ecommerce.domain.user;

import hhplus.ecommerce.TestFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    private User user = TestFixtures.user(1L);

    @Test
    @DisplayName("포인트 충전 테스트")
    void add_point() {

        // When
        User addPointUser = user.addPoint(100_000L);

        // Then
        assertThat(addPointUser.point()).isEqualTo(11_00_000L);
    }

    @Test
    @DisplayName("포인트 차감 테스트")
    void minus_point() {
        // When
        User minusedPointUser = user.minusPoint(100_000L);

        // Then
        assertThat(minusedPointUser.point()).isEqualTo(900_000L);
    }

    @Test
    @DisplayName("결제 시 포인트가 충분한 경우 에러가 발생하지 않음")
    void enough_point_for_pay_not_throw_exception() {
        // Given
        Long payAmount = 100_000L;

        // When && Then
        assertDoesNotThrow(() -> {
            user.isEnoughPointForPay(payAmount);
        });
    }

    @Test
    @DisplayName("결제 시 포인트가 부족하면 에러발생")
    void not_enough_point_for_pay_throw_exception() {
    // given
    Long payAmount = 20_00_000L;

    // when && then
        assertThrows(IllegalArgumentException.class, () -> {
            user.isEnoughPointForPay(payAmount);
        });
    }
}