package hhplus.ecommerce.api.controller;

import hhplus.ecommerce.domain.user.UserPointService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserPointController.class)
class UserPointControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserPointService userPointService;

    @Test
    @DisplayName("포인트 충전 성공")
    void point_charge_success() throws Exception {

        // Given
        Long userId = 1L;
        Long point = 500L;

        given(userPointService.chargePoint(anyLong(), anyLong())).willReturn(point);

        // When && Then
        mockMvc.perform(patch("/charge/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "amount" : 10000
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(500));
    }

    @Test
    @DisplayName("음수 입력 시 포인트 충전 실패")
    void negative_point_charge_fail() throws Exception {
        // Given
        Long userId = 1L;

        // when && then
        mockMvc.perform(patch("/charge/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                 "amount": -10000
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("0원 입력 시 포인트 충전 실패")
    void zero_point_charge_fail() throws Exception {
        // Given
        Long userId = 1L;

        // When && Then
        mockMvc.perform(patch("/ecommerce/api/point/charge/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "amount": 0
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("잔액 조회 성공")
    void read_user_point_success() throws Exception {
        // Given
        Long userId = 1L;
        Long point = 5000L;

        given(userPointService.readPoint(userId)).willReturn(point);

        // When && Then
        mockMvc.perform(get("/ecommerce/api/point/" + userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.point").value(5000L));
    }
}