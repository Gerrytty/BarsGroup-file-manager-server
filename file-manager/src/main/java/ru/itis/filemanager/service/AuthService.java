package ru.itis.filemanager.service;

import ru.itis.filemanager.controller.ControllerResult;
import ru.itis.filemanager.dto.SignInRequest;
import ru.itis.filemanager.dto.SignInResponse;

public interface AuthService {
    SignInResponse signUp(SignInRequest form);

    SignInResponse signIn(SignInRequest form);

    ControllerResult validateToken(String token);
}
