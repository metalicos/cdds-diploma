CREATE TABLE IF NOT EXISTS public."DEVICE_TYPE"
(
    "id"   bigserial,
    "type" text NOT NULL,
    PRIMARY KEY ("id")
);

ALTER TABLE IF EXISTS public."DEVICE_TYPE"
    OWNER to postgres;

CREATE INDEX IF NOT EXISTS IDX_ID_HASH_czkjfnasdhj
    ON public."DEVICE_TYPE" USING hash (id);
CREATE INDEX IF NOT EXISTS IDX_TYPE_HASH_apsodjekj
    ON public."DEVICE_TYPE" USING hash (type);