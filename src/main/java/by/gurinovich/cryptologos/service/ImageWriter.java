package by.gurinovich.cryptologos.service;

import by.gurinovich.cryptologos.clients.Token;
import by.gurinovich.cryptologos.dto.ChainConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Service
public class ImageWriter {

    public void writeLogoImage(byte[] bytes, Token token, ChainConfig config){
        try {
            var inStream = new ByteArrayInputStream(bytes);
            var image = ImageIO.read(inStream);
            var format = token.logoURI()
                    .substring(token.logoURI().lastIndexOf(".")+1);
            var path = getPath(token.address(),
                    config.outputDirectoryName(),
                    format);
            Files.createDirectories(Paths.get(path).getParent());
            ImageIO.write(image, format, new File(path));
            log.info("Wrote {} successfully to {}", token.address(), path);
            if (config.logoReplacements() != null) {
                for (var logoReplacement : config.logoReplacements()) {
                    path = getPath(logoReplacement.currentChainAddress(),
                            logoReplacement.replacesChainId(),
                            format);
                    Files.createDirectories(Paths.get(path).getParent());
                    ImageIO.write(image, format, new File(path));
                    log.info("Wrote {} successfully to {}", logoReplacement.currentChainAddress(), path);
                }
            }
        }
        catch (Exception e){
            log.error("Error while writing image!");
        }
    }

    private String getPath(String address, String dirName, String format){
        StringBuilder builder = new StringBuilder();
        builder.append(dirName)
                .append("/")
                .append(address)
                .append(".")
                .append(format);
        return builder.toString();
    }

}
