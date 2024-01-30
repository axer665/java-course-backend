package ru.netology.cloudstorage.controller;

import ru.netology.cloudstorage.exceptions.MessageConstant;
import ru.netology.cloudstorage.model.AuthRequest;
import ru.netology.cloudstorage.model.AuthResponse;
import ru.netology.cloudstorage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static ru.netology.cloudstorage.exceptions.MessageConstant.LOGIN;
import static ru.netology.cloudstorage.exceptions.MessageConstant.LOGOUT;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping(value = LOGIN)
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        return new ResponseEntity(userService.loginUser(authRequest), HttpStatus.OK);
    }

    @PostMapping(LOGOUT)
    public ResponseEntity<?> logout(@RequestHeader(value = "auth-token", required = false) String authToken) {
        userService.logoutUser(authToken);
        return ResponseEntity.status(HttpStatus.OK)
                .body(MessageConstant.LOGOUT_USER);
    }
}