CREATE TABLE IF NOT EXISTS public."HYDROPONIC_DISPENSER_SETTINGS_V1"
(
    "id"                              serial,
    "setting_detail_id"               bigint,

    "device_uuid"                     text NOT NULL,
    "index"                           numeric,
    "time"                            timestamp with time zone,
    "dispenser_name"                  text,
    "pin_a"                           numeric,
    "regulation_direction"            bool,
    "enable"                          bool,
    "polarity"                        bool,
    "smart_dose"                      bool,
    "total_added_ml"                  double precision,
    "ml_per_ms"                       double precision,
    "target_value"                    double precision,
    "error"                           double precision,
    "recheck_dispenser_after_seconds" numeric,
    "last_dispenser_recheck_time"     numeric,
    "mixing_volume_ml"                numeric,

    "created_timestamp"               timestamp with time zone default now(),
    "updated_timestamp"               timestamp with time zone,

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

ALTER TABLE IF EXISTS public."HYDROPONIC_DISPENSER_SETTINGS_V1"
    OWNER to postgres;

CREATE INDEX IF NOT EXISTS IDX_DEVICE_UUID_HASH_qweqwrq
    ON public."HYDROPONIC_DISPENSER_SETTINGS_V1" USING hash (device_uuid);
CREATE INDEX IF NOT EXISTS IDX_SETTING_DETAIL_ID_HASH_cewrtw
    ON public."HYDROPONIC_DISPENSER_SETTINGS_V1" USING hash (setting_detail_id);