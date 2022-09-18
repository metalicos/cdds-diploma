USE `cdds`;
CREATE INDEX index_device_metadata ON device_metadata (id, uuid, user_id);