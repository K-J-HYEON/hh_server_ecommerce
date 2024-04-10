package hhplus.ecommerce.user.entity;

import hhplus.ecommerce.config.BaseTimeEntity;
import hhplus.ecommerce.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "User")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "point")
    private Long point;

    public Long getId() {
        return userId;
    }

    public User toUser() {
        return new User(getId(), name, address, phoneNumber, point);
    }

    public void updatePoint(User user) {
        this.point = user.point();
    }
}