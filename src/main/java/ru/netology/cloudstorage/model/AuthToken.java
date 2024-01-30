package ru.netology.cloudstorage.model;

import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class AuthToken {

    private String authToken;
}