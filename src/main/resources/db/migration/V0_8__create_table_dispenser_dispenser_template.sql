CREATE TABLE "hydroponic_v1"."dispenser_dispenser_template"
(
    "dispenser_id"          bigint,
    "dispenser_template_id" bigint
);

ALTER TABLE "hydroponic_v1"."dispenser_dispenser_template" OWNER to postgres;

ALTER TABLE "hydroponic_v1"."dispenser_dispenser_template"
    ADD CONSTRAINT "fk_dispenser_dispenser_template_dispenser_id" FOREIGN KEY ("dispenser_id")
        REFERENCES "hydroponic_v1"."dispenser" ("id");

ALTER TABLE "hydroponic_v1"."dispenser_dispenser_template"
    ADD CONSTRAINT "fk_dispenser_dispenser_template_dispenser_template_id" FOREIGN KEY ("dispenser_template_id")
        REFERENCES "hydroponic_v1"."dispenser_template" ("id");
