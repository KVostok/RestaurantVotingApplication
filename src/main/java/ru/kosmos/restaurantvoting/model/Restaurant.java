package ru.kosmos.restaurantvoting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "allJoined",
                attributeNodes = {
                        @NamedAttributeNode(value = "menues", subgraph = "menues-sub")
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "menues-sub",
                                attributeNodes = {
                                        @NamedAttributeNode(value = "dishes", subgraph = "dishes-sub"),
                                        @NamedAttributeNode(value = "votes"),
                                }
                        ),
                        @NamedSubgraph(
                                name = "dishes-sub",
                                attributeNodes = @NamedAttributeNode("dish")
                        )
                }
        )
})
@Table(name = "restaurant", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}, name = "restaurants_unique_name_idx"))
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Restaurant extends NamedEntity {

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "restaurant")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Menu> menues;

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(Restaurant restaurant) {
        super(restaurant.id(), restaurant.getName());
        this.menues = restaurant.getMenues();
    }

}
