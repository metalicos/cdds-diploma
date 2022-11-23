CREATE TABLE "hydroponic_v1"."ec_sensor_ec_sensor_template"
(
    "id"                    bigserial PRIMARY KEY,
    "ec_sensor_id"          bigint,
    "ec_sensor_template_id" bigint
);

ALTER TABLE "hydroponic_v1"."ec_sensor_ec_sensor_template" OWNER to postgres;

ALTER TABLE "hydroponic_v1"."ec_sensor_ec_sensor_template"
    ADD CONSTRAINT "fk_ec_sensor_ec_sensor_template_ec_sensor_id" FOREIGN KEY ("ec_sensor_id")
        REFERENCES "hydroponic_v1"."ec_sensor" ("id");

ALTER TABLE "hydroponic_v1"."ec_sensor_ec_sensor_template"
    ADD CONSTRAINT "fk_ec_sensor_ec_sensor_template_ec_sensor_template_id" FOREIGN KEY ("ec_sensor_template_id")
        REFERENCES "hydroponic_v1"."ec_sensor_template" ("id");
