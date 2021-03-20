package ru.itis.filemanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.itis.filemanager.dto.SignInRequest;
import ru.itis.filemanager.dto.SignInResponse;
import ru.itis.filemanager.service.AuthServiceImpl;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceImpl signUpService;

    @PostMapping("/auth")
    public SignInResponse signIn(@RequestBody SignInRequest request) {
        return signUpService.signIn(request);
    }

    @PostMapping("/reg")
    public SignInResponse signUp(@RequestBody SignInRequest signUpRequest) {
        return signUpService.signUp(signUpRequest);
    }

    @PostMapping("/valid")
    public ControllerResult signUp(@RequestHeader("Authorization") String token) {
        return signUpService.validateToken(token);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({UsernameNotFoundException.class})
    public ControllerResult handleNotFoundError(Exception e) {
        return ControllerResult.failResult(e.getMessage());
    }

}