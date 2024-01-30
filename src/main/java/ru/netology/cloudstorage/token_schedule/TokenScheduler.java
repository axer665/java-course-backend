package ru.netology.cloudstorage.token_schedule;

import ru.netology.cloudstorage.model.AuthToken;
import ru.netology.cloudstorage.security.TokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@EnableScheduling
@RequiredArgsConstructor
public class TokenScheduler {

    private final AuthToken authToken;

    private final TokenUtils tokenUtils;

    @Scheduled(fixedDelay = 4 * 60 * 60 * 1000)
    public void updateToken() {
        log.info("Token registered " + LocalDateTime.now());
        authToken.setAuthToken(tokenUtils.generateToken());
    }
}