package by.gurinovich.cryptologos.dto;

import java.util.List;

public record ChainConfig(
        String chainId,
        String providerType,
        String providerURL,
        String outputDirectoryName,
        List<LogoReplacement> logoReplacements
) {
}
