CREATE TABLE "hydroponic_v1"."dispenser"
(
    "id"                bigserial PRIMARY KEY,
    "device_uuid"       text,
    "updated_timestamp" timestamp with time zone
);

ALTER TABLE "hydroponic_v1"."dispenser" OWNER to postgres;

ALTER TABLE "hydroponic_v1"."dispenser"
    ADD CONSTRAINT "fk_dispenser_device_uuid" FOREIGN KEY ("device_uuid")
        REFERENCES "common"."device" ("uuid");
