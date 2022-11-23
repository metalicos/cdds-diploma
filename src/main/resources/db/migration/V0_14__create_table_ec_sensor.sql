CREATE TABLE "hydroponic_v1"."ec_sensor"
(
    "id"                bigserial PRIMARY KEY,
    "device_uuid"       text,
    "created_timestamp"    timestamp with time zone,
    "updated_timestamp" timestamp with time zone
);

ALTER TABLE "hydroponic_v1"."ec_sensor" OWNER to postgres;

ALTER TABLE "hydroponic_v1"."ec_sensor"
    ADD CONSTRAINT "fk_ec_sensor_device_uuid" FOREIGN KEY ("device_uuid")
        REFERENCES "common"."device" ("uuid");
