USE `cdds`;
CREATE TABLE `hydroponic_settings`
(
    `id`                          BIGINT       NOT NULL AUTO_INCREMENT,
    `uuid`                        VARCHAR(36) NOT NULL,
    `setup_ph_value`              DOUBLE       NULL DEFAULT NULL,
    `setup_tds_value`             BIGINT       NULL DEFAULT NULL,
    `regulate_error_ph`           DOUBLE       NULL DEFAULT NULL,
    `regulate_error_fertilizer`   DOUBLE       NULL DEFAULT NULL,
    `ml_per_millisecond`          DOUBLE       NULL DEFAULT NULL,
    `ph_up_dose_ml`               DOUBLE       NULL DEFAULT NULL,
    `ph_down_dose_ml`             DOUBLE       NULL DEFAULT NULL,
    `fertilizer_dose_ml`          DOUBLE       NULL DEFAULT NULL,
    `recheck_dispensers_after_ms` BIGINT       NULL DEFAULT NULL,
    `restart_counter`             BIGINT       NULL DEFAULT NULL,
    `dispensers_enable`           BIT(1)       NULL DEFAULT NULL,
    `sensors_enable`              BIT(1)       NULL DEFAULT NULL,
    `autotime`                    BIT(1)       NULL DEFAULT NULL,
    `time_zone`                   VARCHAR(300) NULL DEFAULT NULL,
    `wifi_ssid`                   VARCHAR(50)  NULL DEFAULT NULL,
    `wifi_pass`                   VARCHAR(50)  NULL DEFAULT NULL,
    `microcontroller_time`        DATETIME(6)  NULL DEFAULT NULL,
    `created_timestamp`           DATETIME(6)       DEFAULT CURRENT_TIMESTAMP(6),
    `updated_timestamp`           DATETIME(6)       DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_HYDROPONIC_SETTINGS_uuid_DEVICE_METADATA_uuid` FOREIGN KEY (`uuid`) REFERENCES `cdds`.`device_metadata` (`uuid`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
