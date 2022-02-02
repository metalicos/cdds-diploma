USE `cdds`;
CREATE TABLE `hydroponic_data`
(
    `id`                        BIGINT       NOT NULL AUTO_INCREMENT,
    `uuid`                      VARCHAR(500) NOT NULL,
    `ph_value`                  DOUBLE       NULL DEFAULT NULL,
    `temperature_value`         DOUBLE       NULL DEFAULT NULL,
    `tds_value`                 DOUBLE       NULL DEFAULT NULL,
    `is_dispenser_ph_up_open`   BIT(1)       NULL DEFAULT NULL,
    `is_dispenser_ph_down_open` BIT(1)       NULL DEFAULT NULL,
    `is_dispenser_tds_open`     BIT(1)       NULL DEFAULT NULL,
    `microcontroller_time`      DATETIME(6)  NULL DEFAULT NULL,
    `created_timestamp`         DATETIME(6)       DEFAULT CURRENT_TIMESTAMP(6),
    `updated_timestamp`         DATETIME(6)       DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
