package hhplus.ecommerce.user.presentation;

import hhplus.ecommerce.user.application.UserPointService;
import hhplus.ecommerce.user.presentation.dto.req.ChargePointReq;
import hhplus.ecommerce.user.presentation.dto.res.ChargePointRes;
import hhplus.ecommerce.user.presentation.dto.res.UserPointRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Tag(name = "잔액 조회 API", description = "사용자의 잔액 조회 API입니다.")
    @GetMapping("/{userId}")
    public UserPointRes readPoint(@PathVariable Long userId) {
        Long point = userPointService.readPoint(userId);
        return UserPointRes.from(point);
    }



    @Tag(name = "잔액 충전 API", description = "사용자의 잔액을 충전 API입니다.")
    @PostMapping("/charge/{userId}")
    public ChargePointRes chargePoint(@PathVariable Long userId,
                                      @Valid @RequestBody ChargePointReq req) {
        Long balance = userPointService.chargePoint(userId, req.point());
        return ChargePointRes.from(req.point());
    }
}
