package ru.kosmos.restaurantvoting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "votes", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "menu_id"}, name = "votes_unique_user_menu_idx"))
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Votes extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @JsonIgnore
    @OrderColumn
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    @OrderColumn
    private Users user;

    public Votes(Integer id, Menu menu, Users user) {
        super(id);
        this.menu = menu;
        this.user = user;
    }

    @Override
    public String toString() {
        return "\nVotes{" +
                "id=" + id +
                ", userId=" + (user == null ? "null" : user.getId()) +
                ", menuId=" + (user == null ? "null" : menu.getId()) +
                '}';
    }

}
