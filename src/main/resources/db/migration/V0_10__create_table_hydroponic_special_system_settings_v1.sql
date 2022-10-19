CREATE TABLE IF NOT EXISTS public."HYDROPONIC_SPECIAL_SYSTEM_SETTINGS_V1"
(
    "id"                serial,
    "setting_detail_id" bigint,

    "device_uuid"       text NOT NULL,
    "time"              timestamp with time zone,
    "software_version"  text,
    "schedule_amount"   numeric,
    "dispensers_amount" numeric,
    "wifi_rssi"         text,
    "wifi_bsid"         text,
    "local_ip"          text,
    "subnet_mask"       text,
    "gateway_ip"        text,
    "mac_addr"          text,

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

ALTER TABLE IF EXISTS public."HYDROPONIC_SPECIAL_SYSTEM_SETTINGS_V1"
    OWNER to postgres;

CREATE INDEX IF NOT EXISTS IDX_DEVICE_UUID_HASH_ldfosqj
    ON public."HYDROPONIC_SPECIAL_SYSTEM_SETTINGS_V1" USING hash (device_uuid);
CREATE INDEX IF NOT EXISTS IDX_SETTING_DETAIL_ID_HASH_hfhsag
    ON public."HYDROPONIC_SPECIAL_SYSTEM_SETTINGS_V1" USING hash (setting_detail_id);
