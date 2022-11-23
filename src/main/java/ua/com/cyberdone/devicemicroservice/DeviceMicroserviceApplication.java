package ua.com.cyberdone.devicemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class DevicemicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevicemicroserviceApplication.class, args);
    }

}
