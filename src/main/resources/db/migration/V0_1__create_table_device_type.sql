CREATE TABLE public."DEVICE_TYPE"
(
    "id"   bigserial,
    "type" text NOT NULL,
    PRIMARY KEY ("id")
);

ALTER TABLE IF EXISTS public."DEVICE_TYPE"
    OWNER to postgres;