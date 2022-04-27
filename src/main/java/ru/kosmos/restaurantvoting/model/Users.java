package ru.kosmos.restaurantvoting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.kosmos.restaurantvoting.util.validation.NoHtml;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Users extends NamedEntity {

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    @NoHtml  // https://stackoverflow.com/questions/17480809
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    // https://stackoverflow.com/a/12505165/548473
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private String password;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date registered = new Date();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Votes> votes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Roles> roles;

    public Users(Integer id, String name, String email, String password) {
        this(id, name, email, password, true, new Date());
    }

    public Users(Integer id, String name, String email, String password, boolean enabled, Date registered) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
    }

    public Users(Integer id, String name, String email, String password, boolean enabled, Date registered, Set<Roles> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        this.roles = roles;
    }

    public Users(Users user) {
        super(user.id(), user.getName());
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();
        this.registered = user.getRegistered();
        this.roles = user.getRoles();
        this.votes = user.getVotes();
    }

}
