package by.gurinovich.cryptologos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@FeignClient(
        value = "TokensClient",
        url = "${tokens.base-url}")
public interface TokensClient {

    @GetMapping("/{chainId}")
    Map<String, Token> getTokens(@PathVariable("chainId") String chainId);

    @GetMapping
    Optional<byte[]> getTokenLogo(URI uri);
}
