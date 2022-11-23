CREATE TABLE "hydroponic_v1"."system"
(
    "id"                bigserial PRIMARY KEY,
    "device_uuid"       text,
    "updated_timestamp" timestamp with time zone
);

ALTER TABLE "hydroponic_v1"."system" OWNER to postgres;

ALTER TABLE "hydroponic_v1"."system"
    ADD CONSTRAINT "fk_system_device_uuid" FOREIGN KEY ("device_uuid")
        REFERENCES "common"."device" ("uuid");
