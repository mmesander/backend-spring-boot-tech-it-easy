package mesander.com.TechItEasy.security.controllers;

import mesander.com.TechItEasy.security.dtos.input.UserInputDto;
import mesander.com.TechItEasy.security.dtos.output.UserDto;
import mesander.com.TechItEasy.security.models.User;
import mesander.com.TechItEasy.security.services.UserService;
import org.springframework.http.ResponseEntity;
import mesander.com.TechItEasy.exceptions.BadRequestException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> userDtos = userService.getUsers();

        return ResponseEntity.ok().body(userDtos);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDto> getUser(
            @PathVariable("username") String username
    ) {
        UserDto dto = userService.getUser(username);

        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/{username}")
    public ResponseEntity<UserDto> updateKlant(
            @PathVariable("username") String username,
            @RequestBody UserInputDto dto
    ) {

        userService.updateUser(username, dto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Object> deleteKlant(
            @PathVariable("username") String username
    ) {
        userService.deleteUser(username);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(
            @PathVariable("username") String username
    ) {
        return ResponseEntity.ok().body(userService.getAuthorities(username));
    }

    @PostMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @RequestBody String authority) {
        try {

            userService.addAuthority(username, authority);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        userService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }


    // Bonus
    @GetMapping(value = "/user/{username}")
    public ResponseEntity<UserDto> getUser(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable String username
            ) {
        if (Objects.equals(userDetails.getUsername(), username)) {
            UserDto userDto = userService.getUser(username);
            return ResponseEntity.ok(userDto);
        } else {
            throw new BadRequestException();
        }
    }



}
