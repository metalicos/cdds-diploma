CREATE TABLE IF NOT EXISTS public."DEVICE_METADATA"
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

CREATE INDEX IF NOT EXISTS IDX_ID_HASH_sdfjkfs
    ON public."DEVICE_METADATA" USING hash (id);
CREATE INDEX IF NOT EXISTS IDX_UUID_HASH_cccgsdft
    ON public."DEVICE_METADATA" USING hash (uuid);
CREATE INDEX IF NOT EXISTS IDX_OWNER_ID_HASH_qwedgfsdf
    ON public."DEVICE_METADATA" USING hash (owner_id);
CREATE INDEX IF NOT EXISTS IDX_DELEGATION_KEY_HASH_ddfrteas
    ON public."DEVICE_METADATA" USING hash (delegation_key);
