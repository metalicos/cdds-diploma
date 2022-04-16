USE `cdds`;
CREATE TABLE `hydroponic_calibration_data`
(
    `id`                                BIGINT      NOT NULL AUTO_INCREMENT,
    `uuid`                              VARCHAR(36) NOT NULL,
    `tds_calibration_coefficient_value` DOUBLE      NULL DEFAULT NULL,
    `tds_oversampling`                  BIGINT      NULL DEFAULT NULL,
    `ph_calibration_value_point`        BIGINT      NULL DEFAULT NULL,
    `ph_calibration_etalon_value_1`     DOUBLE      NULL DEFAULT NULL,
    `ph_calibration_etalon_value_2`     DOUBLE      NULL DEFAULT NULL,
    `ph_calibration_adc_value_1`        BIGINT      NULL DEFAULT NULL,
    `ph_calibration_adc_value_2`        BIGINT      NULL DEFAULT NULL,
    `ph_calibration_slope`              DOUBLE      NULL DEFAULT NULL,
    `ph_calibration_value_offset`       BIGINT      NULL DEFAULT NULL,
    `ph_oversampling`                   INT         NULL DEFAULT NULL,
    `microcontroller_time`              DATETIME(6) NULL DEFAULT NULL,
    `created_timestamp`                 DATETIME(6)      DEFAULT CURRENT_TIMESTAMP(6),
    `updated_timestamp`                 DATETIME(6)      DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_HYDROPONIC_CALIBRATION_DATA_uuid_DEVICE_METADATA_uuid` FOREIGN KEY (`uuid`) REFERENCES `cdds`.`device_metadata` (`uuid`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
