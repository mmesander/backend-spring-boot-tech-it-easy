package mesander.com.TechItEasy.security.dtos.input;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import mesander.com.TechItEasy.security.models.Authority;

import java.util.Set;

@Getter
@Setter
public class UserInputDto {
    @NotNull(message = "Username is required")
    @Size(min = 2, max = 20, message = "Username must be between 2 and 20 characters")
    private String username;
    @NotNull(message = "Password is required")
    private String password;
    @NotNull(message = "Enabled is required")
    private Boolean enabled;
    private String apikey;
    @NotNull(message = "Email is required")
    private String email;

    @JsonSerialize
    private Set<Authority> authorities;
}
