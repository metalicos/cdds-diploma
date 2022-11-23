CREATE TABLE "hydroponic_v1"."dispense_schedule_template"
(
    "id"                bigserial PRIMARY KEY,
    "name"              text,
    "description"       text,
    "owner_id"          bigint,
    "created_timestamp" timestamp with time zone,
    "updated_timestamp" timestamp with time zone
);

ALTER TABLE "hydroponic_v1"."dispense_schedule_template" OWNER to postgres;
