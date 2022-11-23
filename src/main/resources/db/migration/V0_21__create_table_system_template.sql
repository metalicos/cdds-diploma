CREATE TABLE "hydroponic_v1"."system_template"
(
    "id"                bigserial PRIMARY KEY,
    "name"              text,
    "description"       text,
    "owner_id"          bigint,
    "time"              timestamp with time zone,
    "time_zone"         text,
    "ntp_server"        text,
    "wifi_ssid"         text,
    "wifi_pass"         text,
    "restarts"          bigint,
    "growing_day"       int,
    "is_growing"        boolean,
    "grow_start_date"   bigint,
    "dispensers_enable" boolean,
    "sensors_enable"    boolean,
    "created_timestamp" timestamp with time zone,
    "updated_timestamp" timestamp with time zone
);

ALTER TABLE "hydroponic_v1"."system_template" OWNER to postgres;
