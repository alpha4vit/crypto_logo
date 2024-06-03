package by.gurinovich.cryptologos.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;
import java.util.Map;

@FeignClient(
        value = "TokensClient",
        url = "${tokens.base-url}")
public interface TokensClient {

    @GetMapping("/{chainId}")
    @CircuitBreaker(name = "getTokensCircuitBreaker", fallbackMethod = "getTokensFallback")
    Map<String, Token> getTokens(@PathVariable("chainId") String chainId);



    @GetMapping
    @CircuitBreaker(name = "getTokenLogoCircuitBreaker", fallbackMethod = "getTokenLogoFallback")
    byte[] getTokenLogo(URI uri);

    default Map<String, String> getTokensFallback(Exception exception) {
        System.out.println("token fallback");
        return Map.of();
    }

    default byte[] getTokenLogoFallback(Exception exception) {
        System.out.println("token logo fallback");
        return new byte[]{};
    }
}
