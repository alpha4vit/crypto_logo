package by.gurinovich.cryptologos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class CryptoLogosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoLogosApplication.class, args);
	}

}
