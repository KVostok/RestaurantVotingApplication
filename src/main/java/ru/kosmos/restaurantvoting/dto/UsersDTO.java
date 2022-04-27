package ru.kosmos.restaurantvoting.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import ru.kosmos.restaurantvoting.HasId;
import ru.kosmos.restaurantvoting.util.validation.NoHtml;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UsersDTO extends NamedDTO implements HasId {

    @Email
    @NotBlank
    @Size(max = 100)
    @NoHtml  // https://stackoverflow.com/questions/17480809
    String email;

    @NotBlank
    @Size(min = 5, max = 32)
    String password;

    public UsersDTO(Integer id, String name, String email, String password) {
        super(id, name);
        this.email = email;
        this.password = password;
    }

}
