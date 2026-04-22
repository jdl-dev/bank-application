package pl.jdldev.bankapp.user.api;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.jdldev.bankapp.user.application.UserService;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        return userService.register(registerUserRequest);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable long id) {
        return userService.getUseryId(id);
    }

    @GetMapping
    public Page<UserResponse> getUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }
}