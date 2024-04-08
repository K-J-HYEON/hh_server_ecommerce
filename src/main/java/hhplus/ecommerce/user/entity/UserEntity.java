package hhplus.ecommerce.user.entity;

import hhplus.ecommerce.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "User")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "point")
    private Long point;

    public User toUser() {
        return new User(getId(), name, address, point);
    }

    private Long getId() {
        return null;
    }

    public void updatePoint(User user) {
        this.point = user.point();
    }
}