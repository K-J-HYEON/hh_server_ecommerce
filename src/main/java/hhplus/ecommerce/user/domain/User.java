package hhplus.ecommerce.user.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hhplus.ecommerce.config.BaseTimeEntity;
import hhplus.ecommerce.config.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Builder
@Getter
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private int point;

    public Role getUserRole() {
        return null;
    }

    public String getLoginPwd() {
        return null;
    }

    public char[] getUserId() {
        return new char[0];
    }
}
