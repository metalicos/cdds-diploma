CREATE TABLE public."HYDROPONIC_SETTINGS_V1"
(
    "id"                      bigserial,
    "device_uuid"             text NOT NULL,
    "name"                    text,
    "description"             text,
    "dispensers_sett"         json,
    "dispense_schedules_sett" json,
    "system_sett"             json,
    "special_system_sett"     json,
    "ph_sensor_sett"          json,
    "ec_sensor_sett"          json,
    "type"                    text NOT NULL,
    "updated_timestamp"       timestamp with time zone default now(),
    "created_timestamp"       timestamp with time zone default now(),
    PRIMARY KEY ("device_uuid"),
    FOREIGN KEY ("device_uuid")
        REFERENCES public."DEVICE_METADATA" ("uuid") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
);

ALTER TABLE IF EXISTS public."HYDROPONIC_SETTINGS_V1"
    OWNER to postgres;