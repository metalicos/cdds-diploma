CREATE TABLE public."HYDROPONIC_DATA_V1"
(
    "id"                   bigserial,
    "device_uuid"          text    NOT NULL,
    "ec_solution"          numeric NOT NULL,
    "ph_solution"          numeric NOT NULL,
    "t_solution"           numeric NOT NULL,
    "t_air"                numeric,
    "humidity_air"         numeric,
    "atmospheric_pressure" numeric,
    "created_timestamp"    timestamp with time zone,
    PRIMARY KEY ("device_uuid"),
    FOREIGN KEY ("device_uuid")
        REFERENCES public."DEVICE_METADATA" ("uuid") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
);

ALTER TABLE IF EXISTS public."HYDROPONIC_DATA_V1"
    OWNER to postgres;