package hhplus.ecommerce.user.dto.res;

import hhplus.ecommerce.user.domain.Point;
import lombok.Getter;

@Getter
public class PointRes extends Response<Point>{
    public PointRes(Point data) {
        super("OK", data);
    }
}
