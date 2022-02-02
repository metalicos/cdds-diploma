USE `cdds`;

CREATE TABLE `device_metadata`
(
    `id`                BIGINT       NOT NULL AUTO_INCREMENT,
    `uuid`              VARCHAR(500) NOT NULL UNIQUE,
    `name`              VARCHAR(500) NOT NULL,
    `description`       VARCHAR(500) NOT NULL,
    `device_type`       VARCHAR(300) NOT NULL,
    `access_enabled`    BIT(1)       NULL DEFAULT 0,
    `user_id`           BIGINT       NULL DEFAULT 0,
    `created_timestamp` DATETIME(6)       DEFAULT CURRENT_TIMESTAMP(6),
    `updated_timestamp` DATETIME(6)       DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`id`),
    UNIQUE INDEX `UK_uuid` (`uuid` ASC) VISIBLE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;