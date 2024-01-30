package ru.netology.cloudstorage.service;

import ru.netology.cloudstorage.model.AuthRequest;
import ru.netology.cloudstorage.model.AuthResponse;

public interface UserService {
    AuthResponse loginUser(AuthRequest authRequest);
    void logoutUser(String authToken);
}