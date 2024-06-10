package by.gurinovich.cryptologos.dto;

import java.util.List;

public record ChainConfigListWrapper(
        List<ChainConfig> chainConfigs
) {
}
