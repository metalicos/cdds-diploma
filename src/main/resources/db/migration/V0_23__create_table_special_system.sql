CREATE TABLE "hydroponic_v1"."special_system"
(
    "id"                bigserial PRIMARY KEY,
    "device_uuid"       text,
    "time"              timestamp with time zone,
    "software_version"  text,
    "schedule_amount"   int,
    "dispensers_amount" int,
    "wifi_rssi"         text,
    "wifi_bsid"         text,
    "local_ip"          text,
    "subnet_mask"       text,
    "gateway_ip"        text,
    "mac_addr"          text,
    "created_timestamp" timestamp with time zone,
    "updated_timestamp" timestamp with time zone
);

ALTER TABLE "hydroponic_v1"."special_system" OWNER to postgres;

ALTER TABLE "hydroponic_v1"."special_system"
    ADD CONSTRAINT "fk_special_system_device_uuid" FOREIGN KEY ("device_uuid")
        REFERENCES "common"."device" ("uuid");
