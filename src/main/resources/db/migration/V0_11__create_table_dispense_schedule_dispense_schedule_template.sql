CREATE TABLE "hydroponic_v1"."dispense_schedule_dispense_schedule_template"
(
    "dispense_schedule_id"          bigint,
    "dispense_schedule_template_id" bigint
);

ALTER TABLE "hydroponic_v1"."dispense_schedule_dispense_schedule_template" OWNER to postgres;

ALTER TABLE "hydroponic_v1"."dispense_schedule_dispense_schedule_template"
    ADD CONSTRAINT "fk_disp_schedule_disp_schedule_templ_dispense_schedule_id" FOREIGN KEY ("dispense_schedule_id")
        REFERENCES "hydroponic_v1"."dispense_schedule" ("id");

ALTER TABLE "hydroponic_v1"."dispense_schedule_dispense_schedule_template"
    ADD CONSTRAINT "fk_disp_schedule_disp_schedule_templ_disp_schedule_template_id" FOREIGN KEY ("dispense_schedule_template_id")
        REFERENCES "hydroponic_v1"."dispense_schedule_template" ("id");
