CREATE TABLE IF NOT EXISTS public."HYDROPONIC_SETTING_DETAILS_V1"
(
    "id"                bigserial,
    "name"              text,
    "description"       text,
    "setting_type"      text not null ,

    "created_timestamp" timestamp with time zone default now(),
    "updated_timestamp" timestamp with time zone,
    PRIMARY KEY ("id")
);

ALTER TABLE IF EXISTS public."HYDROPONIC_SETTING_DETAILS_V1" OWNER to postgres;

CREATE INDEX IF NOT EXISTS IDX_ID_HASH_rwqefsdsy ON public."HYDROPONIC_SETTING_DETAILS_V1" USING hash ("id");

CREATE INDEX IF NOT EXISTS IDX_NAME_HASH_eqwerwe ON public."HYDROPONIC_SETTING_DETAILS_V1" USING hash ("name");

CREATE INDEX IF NOT EXISTS IDX_DESCRIPTION_HASH_xcvbdfg ON public."HYDROPONIC_SETTING_DETAILS_V1" USING hash ("description");

CREATE INDEX IF NOT EXISTS IDX_SETTINGS_TYPE_HASH_jntyuit ON public."HYDROPONIC_SETTING_DETAILS_V1" USING hash ("setting_type");
