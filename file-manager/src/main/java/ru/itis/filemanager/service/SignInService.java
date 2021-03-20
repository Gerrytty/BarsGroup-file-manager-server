package ru.itis.filemanager.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.filemanager.dto.Role;
import ru.itis.filemanager.dto.SignInDto;
import ru.itis.filemanager.dto.SignInResponse;
import ru.itis.filemanager.dto.SignUpDto;
import ru.itis.filemanager.model.Userm;
import ru.itis.filemanager.repository.UsersRepository;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

@Service
public class SignInService {

    @Value("${jwt.secret}")
    private String secret;

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public SignInService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public SignInResponse signIn(SignInDto signInData) throws AccessDeniedException {
        Optional<Userm> optionalUser = usersRepository.findUsermByLogin(signInData.getLogin());

        if(optionalUser.isPresent()) {

            Userm user = optionalUser.get();

            if(passwordEncoder.matches(signInData.getPassword(), user.getPass())) {

                return SignInResponse.builder()
                        .username(user.getLogin())
                        .id(user.getId())
                        .token(Jwts.builder()
                                .setSubject(user.getId().toString())
                                .claim("login", user.getLogin())
                                .claim("role", user.getRole())
                                .signWith(SignatureAlgorithm.HS256, secret)
                                .compact())
                        .build();

            }

            else {
                throw new AccessDeniedException("Wrong email/password");
            }

        }

        else {
            throw new AccessDeniedException("User not found");
        }
    }

    public Userm signUp(SignUpDto signUpDto) {

        Userm user = Userm.builder()
                .login(signUpDto.getLogin())
                .pass(passwordEncoder.encode(signUpDto.getPassword()))
                .role(Role.USER)
                .build();

        user = usersRepository.save(user);

        return user;

    }

}
