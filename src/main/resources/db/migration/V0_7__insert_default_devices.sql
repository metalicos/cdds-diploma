USE `cdds`;
START TRANSACTION;
INSERT INTO `cdds`.`device_metadata`(id, uuid, name, description, device_type, access_enabled, user_id)
VALUES (1, '80aeff91-1111-4d67-a44f-479344820f5c', 'Гідропоніка', 'Тестовий стенд', 'HYDROPONIC_V1', 1, 1),
       (2, '80aeff91-bf00-4d67-a44f-479344820f5c', 'Мікроконтроллер', 'Тестовий', 'HYDROPONIC_V1', 1, 1);
COMMIT;