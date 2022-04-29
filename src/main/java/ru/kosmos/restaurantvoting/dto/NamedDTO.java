package ru.kosmos.restaurantvoting.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.kosmos.restaurantvoting.util.validation.NoHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
public class NamedDTO extends BaseDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    @NoHtml
    protected String name;

    public NamedDTO(Integer id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + '[' + name + ']';
    }
}
