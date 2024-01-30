package ru.netology.cloudstorage.service;

import ru.netology.cloudstorage.exceptions.InvalidTokenException;
import ru.netology.cloudstorage.model.AuthToken;

import static ru.netology.cloudstorage.exceptions.MessageConstant.TOKEN_INVALID;
import static ru.netology.cloudstorage.exceptions.MessageConstant.TOKEN_PREFIX;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckTokenServiceImpl implements CheckTokenService {

    private final AuthToken token;

    @Override
    public void testToken(String authToken) {
        if (!authToken.equals(TOKEN_PREFIX + token.getAuthToken())) {
            throw new InvalidTokenException(TOKEN_INVALID);
        }
    }
}
