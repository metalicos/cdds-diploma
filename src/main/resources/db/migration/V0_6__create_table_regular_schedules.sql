USE `cdds`;
CREATE TABLE `regular_schedules`
(
    `id`                BIGINT       NOT NULL AUTO_INCREMENT,
    `uuid`              VARCHAR(500) NOT NULL,
    `name`              VARCHAR(100) NULL DEFAULT NULL,
    `description`       VARCHAR(500) NULL DEFAULT NULL,
    `key_value`         VARCHAR(500) NULL DEFAULT NULL,
    `topic_value`       VARCHAR(500) NULL DEFAULT NULL,
    `monday`            BIT(1)       NULL DEFAULT NULL,
    `tuesday`           BIT(1)       NULL DEFAULT NULL,
    `wednesday`         BIT(1)       NULL DEFAULT NULL,
    `thursday`          BIT(1)       NULL DEFAULT NULL,
    `friday`            BIT(1)       NULL DEFAULT NULL,
    `saturday`          BIT(1)       NULL DEFAULT NULL,
    `sunday`            BIT(1)       NULL DEFAULT NULL,
    `time`              TIME         NULL DEFAULT NULL,
    `value`             VARCHAR(200) NULL DEFAULT NULL,
    `value_type`        VARCHAR(200) NULL DEFAULT NULL,
    `created_timestamp` DATETIME(6)       DEFAULT CURRENT_TIMESTAMP(6),
    `updated_timestamp` DATETIME(6)       DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
