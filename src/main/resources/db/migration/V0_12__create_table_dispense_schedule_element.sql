CREATE TABLE "hydroponic_v1"."dispense_schedule_element"
(
    "id"                            bigserial PRIMARY KEY,
    "dispense_schedule_template_id" bigint,
    "index"                         int,
    "time"                          timestamp with time zone,
    "day"                           int,
    "target_ec"                     double precision,
    "target_ph"                     double precision,
    "ec_error"                      double precision,
    "ph_error"                      double precision,
    "recheck_after_sec"             bigint,
    "created_timestamp"             timestamp with time zone,
    "updated_timestamp"             timestamp with time zone
);

ALTER TABLE "hydroponic_v1"."dispense_schedule_element" OWNER to postgres;

ALTER TABLE "hydroponic_v1"."dispense_schedule_element"
    ADD CONSTRAINT "fk_dispense_schedule_element_dispense_schedule_template_id" FOREIGN KEY ("dispense_schedule_template_id")
        REFERENCES "hydroponic_v1"."dispense_schedule_template" ("id");
