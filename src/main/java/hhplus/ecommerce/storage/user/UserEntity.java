package hhplus.ecommerce.storage.user;

import hhplus.ecommerce.config.BaseTimeEntity;
import hhplus.ecommerce.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "User")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "point")
    private Long point;

    public Long getId() {
        return id;
    }

    public User toUser() {
        return new User(getId(), name, address, phoneNumber, point);
    }

    public void updatePoint(User user) {
        this.point = user.point();
    }
}
