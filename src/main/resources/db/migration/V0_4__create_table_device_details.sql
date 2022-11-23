CREATE TABLE "common"."device_details"
(
    "id"                     bigserial PRIMARY KEY,
    "version"                text,
    "modification"           text,
    "manufactured_timestamp" timestamp with time zone,
    "manufactured_country"   text,
    "sold_timestamp"         timestamp with time zone,
    "sold_country"           text,
    "warranty_from"          timestamp with time zone,
    "warranty_to"            timestamp with time zone,
    "device_uuid"            text
);

ALTER TABLE "common"."device_details" OWNER to postgres;

ALTER TABLE "common"."device_details"
    ADD CONSTRAINT "fk_device_details_device_uuid" FOREIGN KEY ("device_uuid")
        REFERENCES "common"."device" ("uuid");