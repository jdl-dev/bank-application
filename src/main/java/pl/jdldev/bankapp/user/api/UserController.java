package pl.jdldev.bankapp.user.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jdldev.bankapp.user.application.UserService;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    public void register(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        userService.register(registerUserRequest);
    }
}