package ru.itis.filemanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.filemanager.controller.ControllerResult;
import ru.itis.filemanager.dto.Role;
import ru.itis.filemanager.dto.SignInRequest;
import ru.itis.filemanager.dto.SignInResponse;
import ru.itis.filemanager.model.Userm;
import ru.itis.filemanager.repository.UsersRepository;
import ru.itis.filemanager.security.JwtTokenProvider;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public SignInResponse signUp(SignInRequest form) {
        Userm user = Userm.builder()
                .login(form.getLogin())
                .pass(passwordEncoder.encode(form.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return signIn(user);
    }

    @Override
    public SignInResponse signIn(SignInRequest form) {
        Userm user = Userm.builder()
                .login(form.getLogin())
                .pass(passwordEncoder.encode(form.getPassword()))
                .build();
        return signIn(user);
    }

    private SignInResponse signIn(Userm user){
        String token = jwtTokenProvider
                .createToken(userRepository.findUsermByLogin(user.getLogin()));
//                .orElseThrow(() -> new UsernameNotFoundException("username with username: " + user.getLogin() + " not found")));

        return SignInResponse.builder()
                .username(user.getLogin())
                .token(token)
                .build();
    }

    @Override
    public ControllerResult validateToken(String token) {
        return ControllerResult.successResult(String.valueOf(jwtTokenProvider.validateToken(token)));
    }
}