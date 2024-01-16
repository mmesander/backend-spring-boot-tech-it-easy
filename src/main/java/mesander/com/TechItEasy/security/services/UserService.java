package mesander.com.TechItEasy.security.services;

import mesander.com.TechItEasy.exceptions.UsernameNotFoundException;
import mesander.com.TechItEasy.security.dtos.input.UserInputDto;
import mesander.com.TechItEasy.security.dtos.output.UserDto;
import mesander.com.TechItEasy.security.models.Authority;
import mesander.com.TechItEasy.security.models.User;
import mesander.com.TechItEasy.security.repositories.UserRepository;
import mesander.com.TechItEasy.security.utils.RandomStringGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // Transfer Methods
    public static UserDto fromUser(User user) {
        UserDto dto = new UserDto();

        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEnabled(user.isEnabled());
        dto.setApikey(user.getApiKey());
        dto.setEmail(user.getEmail());
        dto.setAuthorities(user.getAuthorities());

        return dto;
    }

    public static User toUser(UserInputDto inputDto) {
        User user = new User();

        user.setUsername(inputDto.getUsername());
        user.setPassword(inputDto.getPassword());
        user.setEnabled(inputDto.getEnabled());
        user.setApiKey(inputDto.getApikey());
        user.setEmail(inputDto.getEmail());

        return user;
    }


    // CRUD Methods
    public List<UserDto> getUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(fromUser(user));
        }

        return collection;
    }

    public UserDto getUser(String username) {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            return fromUser(user.get());
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    public String createUser(UserInputDto inputDto) {
        User user = toUser(inputDto);

        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        user.setApiKey(randomString);

        userRepository.save(user);

        return user.getUsername();
    }

    public String deleteUser(String username) {
        Optional<User> user = userRepository.findById(username);

        if (user.isPresent()) {
            userRepository.deleteById(username);
            return "User " + username + " is deleted";
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public UserDto updateUser(String username, UserInputDto inputDto) {
        Optional<User> presentUser = userRepository.findById(username);

        if (presentUser.isPresent()) {
            User updateUser = presentUser.get();

            updateUser.setEmail(inputDto.getEmail());

            User updatedUser = userRepository.save(updateUser);

            return fromUser(updatedUser);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public Set<Authority> getAuthorities(String username) {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            UserDto dto = fromUser(user.get());
            return dto.getAuthorities();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public UserDto addAuthority(String username, Authority authority) {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            Set<Authority> authorities = user.get().getAuthorities();
            authorities.add(authority);
            return fromUser(user.get());
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public void removeAuthority(String username, String authority) {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            Authority toRemove = user.get().getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
            user.get().removeAuthority(toRemove);
            userRepository.save(user.get());
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
