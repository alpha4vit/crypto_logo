package by.gurinovich.cryptologos.tasks;

import by.gurinovich.cryptologos.clients.TokensClient;
import by.gurinovich.cryptologos.service.ImageWriter;
import by.gurinovich.cryptologos.util.parser.JsonParser;
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
    private final JsonParser jsonParser;
    private final ImageWriter imageWriter;

    @Scheduled(initialDelay = 1)
    private void updateCurrencyLogos() {
        log.info("Logo update started");
        var chainConfigs = jsonParser.convertFromJson("chainConfig.json");
        chainConfigs.parallelStream().forEach(config -> {
            log.info(config.providerURL());
            var response = tokensClient.getTokens(URI.create(config.providerURL()));
            response.forEach((key, token) -> {
                var logo = tokensClient.getTokenLogo(URI.create(token.logoURI()));
                imageWriter.writeLogoImage(logo, token, config);
            });
        });
        log.info("Logo update finished");
    }
}
