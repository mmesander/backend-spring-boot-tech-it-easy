package mesander.com.TechItEasy.security.dtos.output;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import mesander.com.TechItEasy.security.models.Authority;

import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String username;
    private String password;
    private Boolean enabled;
    private String apikey;
    private String email;

    @JsonSerialize
    private Set<Authority> authorities;
}
