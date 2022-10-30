CREATE TABLE IF NOT EXISTS public."HYDROPONIC_DATA_V1"
(
    "id"                   bigserial,
    "device_uuid"          text    NOT NULL,
    "ec_solution"          double precision NOT NULL,
    "ph_solution"          double precision NOT NULL,
    "t_solution"           double precision NOT NULL,
    "t_air"                double precision,
    "humidity_air"         double precision,
    "atmospheric_pressure" double precision,
    "created_timestamp"    timestamp with time zone,
    PRIMARY KEY ("device_uuid"),
    FOREIGN KEY ("device_uuid")
        REFERENCES public."DEVICE_METADATA" ("uuid") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
);

ALTER TABLE IF EXISTS public."HYDROPONIC_DATA_V1" OWNER to postgres;

CREATE INDEX IF NOT EXISTS IDX_ID_HASH_lwefssdfsd ON public."HYDROPONIC_DATA_V1" USING hash ("id");

CREATE INDEX IF NOT EXISTS IDX_DEVICE_UUID_HASH_dfsdhadfasd ON public."HYDROPONIC_DATA_V1" USING hash ("device_uuid");
