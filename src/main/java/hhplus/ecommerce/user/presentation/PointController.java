package hhplus.ecommerce.user.presentation;

import hhplus.ecommerce.user.application.UserPointService;
import hhplus.ecommerce.user.dto.req.ChargePointReq;
import hhplus.ecommerce.user.dto.res.ChargePointRes;
import hhplus.ecommerce.user.dto.res.UserPointRes;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ecommerce/api/point")
public class PointController {

    private final UserPointService userPointService;

    public PointController(UserPointService userPointService) {
        this.userPointService = userPointService;
    }

    @GetMapping("/{userId}")
    public UserPointRes readPoint(@PathVariable Long userId) {
        Long point = userPointService.readPoint(userId);
        return UserPointRes.from(point);
    }

    @PostMapping("/charge/{userId}")
    public ChargePointRes chargePoint(@PathVariable Long userId,
                                      @Valid @RequestBody ChargePointReq req) {
        Long balance = userPointService.chargePoint(userId, req.point());
        return ChargePointRes.from(req.point());
    }
}
