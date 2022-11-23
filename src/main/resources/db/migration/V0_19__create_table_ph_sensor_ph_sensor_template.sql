CREATE TABLE "hydroponic_v1"."ph_sensor_ph_sensor_template"
(
    "id"                    bigserial PRIMARY KEY,
    "ph_sensor_template_id" bigint,
    "ph_sensor_id"          bigint
);

ALTER TABLE "hydroponic_v1"."ph_sensor_ph_sensor_template" OWNER to postgres;

ALTER TABLE "hydroponic_v1"."ph_sensor_ph_sensor_template"
    ADD CONSTRAINT "fk_ph_sensor_ph_sensor_template_ph_sensor_template_id" FOREIGN KEY ("ph_sensor_template_id")
        REFERENCES "hydroponic_v1"."ph_sensor_template" ("id");

ALTER TABLE "hydroponic_v1"."ph_sensor_ph_sensor_template"
    ADD CONSTRAINT "fk_ph_sensor_ph_sensor_template_ph_sensor_id" FOREIGN KEY ("ph_sensor_id")
        REFERENCES "hydroponic_v1"."ph_sensor" ("id");
