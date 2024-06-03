package by.gurinovich.cryptologos;

import by.gurinovich.cryptologos.clients.TokensClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import java.net.URI;

@Slf4j
@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class CryptoLogosApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(CryptoLogosApplication.class, args);
		log.info("Logo update started");
		var tokensClient = context.getBean(TokensClient.class);
		var response = tokensClient.getTokens("10");
		response.forEach((key, token) -> {
			var logo = tokensClient.getTokenLogo(URI.create(token.getLogoURI()));
			log.info(logo.toString());
		});
		log.info("Logo update finished");
	}

}
