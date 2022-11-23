CREATE TABLE "hydroponic_v1"."data"
(
    "id"                   bigserial PRIMARY KEY,
    "device_uuid"          text,
    "ec_solution"          double precision,
    "ph_solution"          double precision,
    "t_solution"           double precision,
    "t_air"                double precision,
    "humidity_air"         double precision,
    "atmospheric_pressure" double precision,
    "created_timestamp"    timestamp with time zone,
    "updated_timestamp"    timestamp with time zone
);

ALTER TABLE "hydroponic_v1"."data" OWNER to postgres;

ALTER TABLE "hydroponic_v1"."data"
    ADD CONSTRAINT "fk_data_device_uuid" FOREIGN KEY ("device_uuid")
        REFERENCES "common"."device" ("uuid");
