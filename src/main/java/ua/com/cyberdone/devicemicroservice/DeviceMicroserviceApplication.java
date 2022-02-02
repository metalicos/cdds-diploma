package ua.com.cyberdone.devicemicroservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.service.MqttService;

@Slf4j
@Service
@EnableScheduling
@EnableEurekaClient
@SpringBootApplication
@RequiredArgsConstructor
public class DeviceMicroserviceApplication implements CommandLineRunner {
    public final MqttService mqttService;

    public static void main(String[] args) {
        SpringApplication.run(DeviceMicroserviceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        mqttService.start();
    }
}
