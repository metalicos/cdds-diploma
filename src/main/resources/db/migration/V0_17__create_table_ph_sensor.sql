CREATE TABLE "hydroponic_v1"."ph_sensor"
(
    "id"                bigserial PRIMARY KEY,
    "device_uuid"       text,
    "created_timestamp"    timestamp with time zone,
    "updated_timestamp" timestamp with time zone
);

ALTER TABLE "hydroponic_v1"."ph_sensor" OWNER to postgres;

ALTER TABLE "hydroponic_v1"."ph_sensor"
    ADD CONSTRAINT "fk_ph_sensor_device_uuid" FOREIGN KEY ("device_uuid")
        REFERENCES "common"."device" ("uuid");
