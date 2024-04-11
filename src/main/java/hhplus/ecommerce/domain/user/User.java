package hhplus.ecommerce.domain.user;

public record User (
    Long id,
    String name,
    String address,
    String phoneNumber,
    Long point
) { ;

    public User addPoint(Long chargePoint) {
        return new User(id, name, address, phoneNumber, point + chargePoint);
    }

    public User decreasePoint(Long amount) {
        return new User(id, name, address, phoneNumber, point - amount);
    }
}