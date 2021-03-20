package ru.itis.filemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.filemanager.dto.SignInDto;
import ru.itis.filemanager.dto.SignInResponse;
import ru.itis.filemanager.dto.SignUpDto;
import ru.itis.filemanager.service.SignInService;

import java.nio.file.AccessDeniedException;

@RestController
public class SignInController {

    private final SignInService signInService;

    @Autowired
    public SignInController(SignInService signInService) {
        this.signInService = signInService;
    }

    @PostMapping("/auth")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInDto signInDto) throws AccessDeniedException {
        return ResponseEntity.ok(signInService.signIn(signInDto));
    }

    @PostMapping("/reg")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {

        signInService.signUp(signUpDto);

        return ResponseEntity.ok("200");

    }

}