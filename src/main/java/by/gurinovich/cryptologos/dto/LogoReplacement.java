package by.gurinovich.cryptologos.dto;

public record LogoReplacement(
        String currentChainAddress,
        String replacesChainId,
        String addressTo
) {
}
