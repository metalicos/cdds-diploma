USE `cdds`;
CREATE TABLE `hydroponic_data`
(
    `id`                                BIGINT      NOT NULL AUTO_INCREMENT,
    `uuid`                              VARCHAR(36) NOT NULL,
    `ph_value`                          DOUBLE      NULL DEFAULT NULL,
    `temperature_value`                 DOUBLE      NULL DEFAULT NULL,
    `tds_value`                         DOUBLE      NULL DEFAULT NULL,

    `fertiliser_ppm`                    INTEGER     NULL DEFAULT NULL,
    `ph_regulation_ppm`                 INTEGER     NULL DEFAULT NULL,
    `starting_solution_ppm`             INTEGER     NULL DEFAULT NULL,
    `last_regulation_is_tds_regulation` BIT(1)      NULL DEFAULT NULL,

    `is_dispenser_ph_up_open`           BIT(1)      NULL DEFAULT NULL,
    `is_dispenser_ph_down_open`         BIT(1)      NULL DEFAULT NULL,
    `is_dispenser_tds_open`             BIT(1)      NULL DEFAULT NULL,
    `microcontroller_time`              DATETIME(6) NULL DEFAULT NULL,
    `created_timestamp`                 DATETIME(6)      DEFAULT CURRENT_TIMESTAMP(6),
    `updated_timestamp`                 DATETIME(6)      DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_HYDROPONIC_DATA_uuid_DEVICE_METADATA_uuid` FOREIGN KEY (`uuid`) REFERENCES `cdds`.`device_metadata` (`uuid`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
