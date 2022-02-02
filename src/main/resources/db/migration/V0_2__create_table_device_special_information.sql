USE `cdds`;
CREATE TABLE `device_special_information`
(
    `id`                BIGINT       NOT NULL AUTO_INCREMENT,
    `uuid`              VARCHAR(500) NOT NULL,
    `wifi_ssid`         VARCHAR(30)  NULL DEFAULT NULL,
    `wifi_pass`         VARCHAR(30)  NULL DEFAULT NULL,
    `wifi_rssi`         INT          NULL DEFAULT NULL,
    `wifi_bsid`         VARCHAR(200) NULL DEFAULT NULL,
    `local_ip`          VARCHAR(200) NULL DEFAULT NULL,
    `subnet_mask`       VARCHAR(200) NULL DEFAULT NULL,
    `gateway_ip`        VARCHAR(200) NULL DEFAULT NULL,
    `mac_addr`          VARCHAR(200) NULL DEFAULT NULL,
    `created_timestamp` DATETIME(6)       DEFAULT CURRENT_TIMESTAMP(6),
    `updated_timestamp` DATETIME(6)       DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
