package ru.itis.filemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.filemanager.sequrity.jwt.details.UserDetailsImpl;
import ru.itis.filemanager.service.UsersService;

@RestController
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();

        if (!id.equals(userDetails.getUserId())) {
            return ResponseEntity.ok("402");
        }

        return ResponseEntity.ok(usersService.getUserInfo(id));

    }

}
