package ru.kosmos.restaurantvoting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id"}, name = "roles_unique_user_role_idx"))
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Roles extends BaseEntity implements GrantedAuthority {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    @ToString.Exclude
    private Users user;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @ToString.Exclude
    private Role role;

    public Roles(Integer id, Users user, Role role) {
        super(id);
        this.user = user;
        this.role = role;
    }

    //    https://stackoverflow.com/a/19542316/548473
    @Override
    public String getAuthority() {
        return "ROLE_" + getRole().getName();
    }

}
