CREATE TABLE "hydroponic_v1"."ph_sensor_template"
(
    "id"                bigserial PRIMARY KEY,
    "name"              text,
    "description"       text,
    "owner_id"          bigint,
    "time"              timestamp with time zone,
    "point"             double precision,
    "value"             double precision[],
    "adc"               int[],
    "slope"             double precision,
    "adc_offset"        int,
    "oversampling"      int,
    "created_timestamp" timestamp with time zone,
    "updated_timestamp" timestamp with time zone
);

ALTER TABLE "hydroponic_v1"."ph_sensor_template" OWNER to postgres;
