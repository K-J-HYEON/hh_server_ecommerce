package hhplus.ecommerce.user.domain;

public record User (
    Long userId,
    String name,
    String address,
    String phoneNumber,
    Long point
) { ;

    public User addPoint(Long chargePoint) {
        return new User(userId, name, address, phoneNumber, point + chargePoint);
    }

    public User decreasePoint(Long amount) {
        return new User(userId, name, address, phoneNumber, point - amount);
    }
}