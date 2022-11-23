CREATE TABLE "hydroponic_v1"."dispenser_scheduling"
(
    "id"                           bigserial PRIMARY KEY,
    "index"                        int,
    "label"                        text,
    "dose_ml"                      double precision,
    "is_active"                    bool,
    "dispense_schedule_element_id" bigint,
    "created_timestamp"            timestamp with time zone,
    "updated_timestamp"            timestamp with time zone
);

ALTER TABLE "hydroponic_v1"."dispenser_scheduling" OWNER to postgres;

ALTER TABLE "hydroponic_v1"."dispenser_scheduling"
    ADD CONSTRAINT "fk_dispenser_scheduling_dispense_schedule_element_id" FOREIGN KEY ("dispense_schedule_element_id")
        REFERENCES "hydroponic_v1"."dispense_schedule_element" ("id");
