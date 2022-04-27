package ru.kosmos.restaurantvoting.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;
import java.util.Objects;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DishesDTO extends BaseDTO {

    @NotNull
    Integer price;

    @ConstructorProperties({"dishId", "price"})
    public DishesDTO(Integer dishId, Integer price) {
        super(dishId);
        this.price = price;
    }

}
