CREATE TABLE IF NOT EXISTS public."HYDROPONIC_DISPENSE_SCHEDULE_SETTINGS_V1"
(
    "id"                serial,
    "setting_detail_id" bigint,

    "device_uuid"       text NOT NULL,
    "index"             numeric,
    "time"              timestamp with time zone,
    "day"               numeric,
    "target_ec"         double precision,
    "target_ph"         double precision,
    "ec_error"          double precision,
    "ph_error"          double precision,
    "recheck_after_sec" numeric,
    "is_active"         bit[],
    "dose_ml"           double precision[],

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

ALTER TABLE IF EXISTS public."HYDROPONIC_DISPENSE_SCHEDULE_SETTINGS_V1"
    OWNER to postgres;

CREATE INDEX IF NOT EXISTS IDX_DEVICE_UUID_HASH_yeyeeyey
    ON public."HYDROPONIC_DISPENSE_SCHEDULE_SETTINGS_V1" USING hash (device_uuid);
CREATE INDEX IF NOT EXISTS IDX_SETTING_DETAIL_ID_HASH_vwvw
    ON public."HYDROPONIC_DISPENSE_SCHEDULE_SETTINGS_V1" USING hash (setting_detail_id);
