package hhplus.ecommerce.domain.user;

import hhplus.ecommerce.TestFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UserPointManagerTest {

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserReader userReader;
    @InjectMocks
    private UserPointManager userPointManager;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userReader = mock(UserReader.class);

        userPointManager = new UserPointManager(userReader, userRepository);
    }

    @Test
    @DisplayName("포인트 충전 성공")
    void charge_point_success() {

        // given
        User user = TestFixtures.user(1L);
        Long chargePoint = 100_000L;

        assertThat(user.point()).isEqualTo(10_00_000L);

        given(userReader.readById(anyLong())).willReturn(user);
        given(userRepository.updateUserPoint(any())).willReturn(user.addPoint(chargePoint));

        // when
        User chargedUser = userPointManager.chargePoint(user.id(), chargePoint);

        // then
        assertThat(chargedUser.point()).isEqualTo(11_00_000L);
    }


    @Test
    @DisplayName("포인트 차감 성공")
    void when_enough_point_use_point_success() {
        // given
        User user = TestFixtures.user(1L);
        Long payAmount = 400_000L;

        given(userReader.readByIdWithLock(anyLong())).willReturn(user);
        given(userRepository.updateUserPoint(any())).willReturn(user.minusPoint(payAmount));

        // when
        User usePoint = userPointManager.usePoint(user, payAmount);

        // then
        assertThat(usePoint.point()).isEqualTo(600_000L);
    }
}