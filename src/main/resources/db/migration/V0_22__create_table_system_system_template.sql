CREATE TABLE "hydroponic_v1"."system_system_template"
(
    "id"                 bigserial PRIMARY KEY,
    "system_template_id" bigint,
    "system_id"          bigint
);

ALTER TABLE "hydroponic_v1"."system_system_template" OWNER to postgres;

ALTER TABLE "hydroponic_v1"."system_system_template"
    ADD CONSTRAINT "fk_system_system_template_system_template_id" FOREIGN KEY ("system_template_id")
        REFERENCES "hydroponic_v1"."system_template" ("id");

ALTER TABLE "hydroponic_v1"."system_system_template"
    ADD CONSTRAINT "fk_system_system_template_system_id" FOREIGN KEY ("system_id")
        REFERENCES "hydroponic_v1"."system" ("id");
