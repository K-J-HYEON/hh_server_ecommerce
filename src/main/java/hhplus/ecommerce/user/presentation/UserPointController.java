package hhplus.ecommerce.user.presentation;

import hhplus.ecommerce.user.application.UserPointService;
import hhplus.ecommerce.user.dto.req.ChargePointReq;
import hhplus.ecommerce.user.dto.res.ChargePointRes;
import hhplus.ecommerce.user.dto.res.UserPointRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@Tag(name = "포인트", description = "포인트 충전 & 조회 관련 api 입니다.")
@RestController
@RequestMapping("/ecommerce/api/point")
public class UserPointController {

    private final UserPointService userPointService;

    public UserPointController(UserPointService userPointService) {
        this.userPointService = userPointService;
    }


    @Operation(summary = "포인트 조회 메서드", description = "포인 조회 메서드입니다.", tags = "포인트")	// (2)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = UserPointRes.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = UserPointRes.class)))
    })
    @GetMapping("/{userId}")
    public UserPointRes readPoint(@PathVariable Long userId) {
        Long point = userPointService.readPoint(userId);
        return UserPointRes.from(point);
    }


    @Operation(summary = "포인트 충전 메서드", description = "포인트 충전 메서드입니다.", tags = "포인트")	// (2)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ChargePointRes.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ChargePointRes.class)))
    })
    @PostMapping("/charge/{userId}")
    public ChargePointRes chargePoint(@PathVariable Long userId,
                                      @Valid @RequestBody ChargePointReq req) {
        Long balance = userPointService.chargePoint(userId, req.point());
        return ChargePointRes.from(req.point());
    }
}
