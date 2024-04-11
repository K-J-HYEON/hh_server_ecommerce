package hhplus.ecommerce.storage.user;

import hhplus.ecommerce.config.BaseTimeEntity;
import hhplus.ecommerce.domain.user.User;
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

    public User toUser() {
        return new User(userId, name, address, phoneNumber, point);
    }

    public void updatePoint(User user) {
        this.point = user.point();
    }
}