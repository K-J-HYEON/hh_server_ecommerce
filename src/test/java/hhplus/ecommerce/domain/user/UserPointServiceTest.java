package hhplus.ecommerce.domain.user;

import hhplus.ecommerce.TestFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UserPointServiceTest {

    private UserReader userReader;
    private UserPointManager userPointManager;
    private UserPointService userPointService;

    @BeforeEach
    void setUp() {
        userReader = mock(UserReader.class);
        userPointManager = mock(UserPointManager.class);
        userPointService = new UserPointService(userReader, userPointManager);
    }

    @Test
    @DisplayName("잔액 충전 성공")
    void pointcharge_success() {
        // Given
        Long userId = 1L;
        Long chargingPoint = 1000L;
        User user = TestFixtures.user(userId);

        given(userReader.retrieveByUserId(userId)).willReturn(user);
        given(userPointManager.chargePoint(user, chargingPoint))
                .willReturn(new User(user.id(), user.name(), user.address(), user.phoneNumber(), user.point() + chargingPoint));

        // When
        Long point = userPointService.chargePoint(userId, chargingPoint);

        // Then
        assertThat(point).isEqualTo(10_00_000L + 1000L);
    }

    @Test
    @DisplayName("잔액 조회 성공")
    void retrieve_point_success() {
        // Given
        Long userId = 1L;

        given(userReader.retrieveByUserId(userId)).willReturn(TestFixtures.user(userId));

        // when
        Long point = userPointService.readPoint(userId);

        // then
        assertThat(point).isEqualTo(10_00_000L);
    }
}