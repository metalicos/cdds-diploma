CREATE TABLE IF NOT EXISTS public."HYDROPONIC_SYSTEM_SETTINGS_V1"
(
    "id"                serial,
    "setting_detail_id" bigint,

    "device_uuid"       text NOT NULL,
    "time"              timestamp with time zone,
    "time_zone"         text,
    "ntp_server"        text,
    "wifi_ssid"         text,
    "wifi_pass"         text,
    "restarts"          numeric,
    "growing_day"       numeric,
    "is_growing"        boolean,
    "grow_start_date"   numeric,
    "dispensers_enable" boolean,
    "sensors_enable"    boolean,

    "created_timestamp" timestamp with time zone default now(),
    "updated_timestamp" timestamp with time zone,

    PRIMARY KEY ("id"),
    FOREIGN KEY ("device_uuid")
        REFERENCES public."DEVICE_METADATA" ("uuid") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID,
    FOREIGN KEY ("setting_detail_id")
        REFERENCES public."HYDROPONIC_SETTING_DETAILS_V1" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
);

ALTER TABLE IF EXISTS public."HYDROPONIC_SYSTEM_SETTINGS_V1"
    OWNER to postgres;

CREATE INDEX IF NOT EXISTS IDX_DEVICE_UUID_HASH_qwej
    ON public."HYDROPONIC_SYSTEM_SETTINGS_V1" USING hash (device_uuid);
CREATE INDEX IF NOT EXISTS IDX_SETTING_DETAIL_ID_HASH_drf
    ON public."HYDROPONIC_SYSTEM_SETTINGS_V1" USING hash (setting_detail_id);
