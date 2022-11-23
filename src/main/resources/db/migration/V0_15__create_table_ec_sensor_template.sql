CREATE TABLE "hydroponic_v1"."ec_sensor_template"
(
    "id"                bigserial PRIMARY KEY,
    "name"              text,
    "description"       text,
    "owner_id"          bigint,
    "time"              timestamp with time zone,
    "k_low_point"       double precision,
    "k_high_point"      double precision,
    "raw_ec"            double precision,
    "created_timestamp" timestamp with time zone,
    "updated_timestamp" timestamp with time zone
);

ALTER TABLE "hydroponic_v1"."ec_sensor_template" OWNER to postgres;
