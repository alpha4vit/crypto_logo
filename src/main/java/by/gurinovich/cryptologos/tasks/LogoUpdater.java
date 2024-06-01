package by.gurinovich.cryptologos.tasks;

import by.gurinovich.cryptologos.clients.TokensClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.net.URI;

@Slf4j
@Component
@RequiredArgsConstructor
public class LogoUpdater {

    private final TokensClient tokensClient;

    @Scheduled(initialDelay = 1)
    private void updateCurrencyLogos(){
        log.info("Logo update started");
        var response = tokensClient.getTokens("10");
        response.forEach((key, token) -> {
            var logo = tokensClient.getTokenLogo(URI.create(token.getLogoURI()));
            log.info(logo.toString());
        });
        log.info("Logo update finished");
    }
}
