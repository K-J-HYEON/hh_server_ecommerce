package hhplus.ecommerce.api.controller;

import hhplus.ecommerce.api.dto.request.ChargePointRequest;
import hhplus.ecommerce.api.dto.response.BalanceResponse;
import hhplus.ecommerce.api.dto.response.ChargePointResponse;
import hhplus.ecommerce.domain.user.UserPointService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ecommerce/api/point")
public class UserPointController {

    private final UserPointService userPointService;

    public UserPointController(UserPointService userPointService) {
        this.userPointService = userPointService;
    }

    @Tag(name = "잔액 조회 API", description = "사용자의 잔액 조회하는 API입니다.")
    @GetMapping("/{userId}")
    public BalanceResponse getBalance(@PathVariable("userId") Long userId) {
        Long balance = userPointService.readPoint(userId);
        return BalanceResponse.from(balance);
    }

    @Tag(name = "잔액 충전 API", description = "사용자의 잔액을 충전하는 API입니다.")
    @PostMapping("/charge/{userId}")
    public ChargePointResponse charge(@PathVariable("userId") Long userId,
                                      @Valid @RequestBody ChargePointRequest request) {
        Long balance = userPointService.chargePoint(userId, request.point());
        return ChargePointResponse.from(balance);
    }
}