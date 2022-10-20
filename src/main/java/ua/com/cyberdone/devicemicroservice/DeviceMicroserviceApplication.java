package ua.com.cyberdone.devicemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJdbcRepositories
@EnableAsync
public class DevicemicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevicemicroserviceApplication.class, args);
    }

}
