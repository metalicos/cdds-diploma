USE `cdds`;
CREATE TABLE `delegated_device_control`
(
    `id`                      BIGINT       NOT NULL AUTO_INCREMENT,
    `delegated_user_username` VARCHAR(100) NOT NULL,
    `comment`                 VARCHAR(500) NULL DEFAULT NULL,
    `device_uuid`             VARCHAR(36)  NOT NULL,
    `created_timestamp`       DATETIME(6)       DEFAULT CURRENT_TIMESTAMP(6),
    `updated_timestamp`       DATETIME(6)       DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    `delegation_status`       VARCHAR(10)  NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_DELEGATED_DEVICE_CONTROL_device_uuid_DEVICE_METADATA_uuid` FOREIGN KEY (`device_uuid`) REFERENCES `cdds`.`device_metadata` (`uuid`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
