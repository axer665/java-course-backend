package ru.netology.cloudstorage.security;

import org.springframework.stereotype.Service;

import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Service
@NoArgsConstructor
public class TokenUtils {

    public String generateToken() {
        String id = UUID.randomUUID().toString().replace("-", "");
        return id;
    }
}