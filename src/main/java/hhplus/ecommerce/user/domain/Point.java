package hhplus.ecommerce.user.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Point {
    private int amount;

    @Builder
    private Point(int amount) {
        this.amount = amount;
    }
}
