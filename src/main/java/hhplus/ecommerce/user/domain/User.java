package hhplus.ecommerce.user.domain;

public record User (
    Long id,
    String name,
    String address,
    Long point
) {
    public User addPoint(Long chargePoint) {
        return new User(id, name, address, point + chargePoint);
    }
}