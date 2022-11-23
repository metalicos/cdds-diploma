CREATE TABLE "hydroponic_v1"."dispense_schedule"
(
    "id"                bigserial PRIMARY KEY,
    "device_uuid"       text,
    "updated_timestamp" timestamp with time zone
);

ALTER TABLE "hydroponic_v1"."dispense_schedule" OWNER to postgres;

ALTER TABLE "hydroponic_v1"."dispense_schedule"
    ADD CONSTRAINT "fk_dispense_schedule_device_uuid" FOREIGN KEY ("device_uuid")
        REFERENCES "common"."device" ("uuid");
