package by.gurinovich.cryptologos.util.parser;

import by.gurinovich.cryptologos.dto.ChainConfig;
import by.gurinovich.cryptologos.dto.ChainConfigListWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class JsonParser {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<ChainConfig> convertFromJson(String path){
        try {
            var config = objectMapper.readValue(new File("src/main/resources/chainConfig.json"), ChainConfigListWrapper.class);
            return config.chainConfigs();
        } catch (IOException e) {
            log.error("Failed to read json chain config from {}", path);
            throw new RuntimeException(e);
        }
    }

}
