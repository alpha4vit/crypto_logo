package by.gurinovich.cryptologos.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;
import java.util.Map;

@FeignClient(
        value = "TokensClient",
        url = "${tokens.base-url}")
public interface TokensClient {

    @GetMapping
    @CircuitBreaker(name = "getTokensCircuitBreaker", fallbackMethod = "getTokensFallback")
    Map<String, Token> getTokens(URI uri);

    @GetMapping
    @CircuitBreaker(name = "getTokenLogoCircuitBreaker", fallbackMethod = "getTokenLogoFallback")
    byte[] getTokenLogo(URI uri);

    default Map<String, String> getTokensFallback(URI uri, Exception exception) {
        System.out.println("token fallback "+uri);
        return Map.of();
    }

    default byte[] getTokenLogoFallback(URI uri, Exception exception) {
        System.out.println("token logo fallback " + uri.getPath());
        return new byte[]{};
    }
}
