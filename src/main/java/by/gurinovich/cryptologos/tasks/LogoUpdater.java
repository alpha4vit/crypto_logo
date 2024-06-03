package by.gurinovich.cryptologos.tasks;

import by.gurinovich.cryptologos.clients.TokensClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class LogoUpdater {

    private final TokensClient tokensClient;

    @Scheduled(initialDelay = 1)
    private void updateCurrencyLogos() {

    }
}
