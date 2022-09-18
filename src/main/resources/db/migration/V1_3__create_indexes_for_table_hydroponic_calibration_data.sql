USE `cdds`;
CREATE INDEX index_hydroponic_calibration_data ON hydroponic_calibration_data (id, uuid, created_timestamp);