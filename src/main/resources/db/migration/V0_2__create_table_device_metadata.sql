CREATE TABLE public."DEVICE_METADATA"
(
    "id"                bigserial,
    "uuid"              text NOT NULL,
    "name"              text,
    "description"       text,
    "owner_id"          bigint,
    "delegation_key"    text,
    "logo"              bytea,
    "device_type_id"    bigint,
    "created_timestamp" timestamp with time zone,
    PRIMARY KEY ("uuid")
);

ALTER TABLE IF EXISTS public."DEVICE_METADATA"
    OWNER to postgres;