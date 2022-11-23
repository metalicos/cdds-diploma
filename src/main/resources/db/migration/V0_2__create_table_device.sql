CREATE TABLE "common"."device"
(
    "id"                        bigserial PRIMARY KEY,
    "name"                      text,
    "description"               text,
    "type"                      text,
    "uuid"                      text,
    "owner_id"                  bigint,
    "delegated_accounts_number" int,
    "delegatable"               bool,
    "repaired_amount_number"    int,
    "created_timestamp"         timestamp with time zone,
    "last_repaired_timestamp"   timestamp with time zone
);

ALTER TABLE "common"."device" OWNER to postgres;

ALTER TABLE "common"."device"
    ADD CONSTRAINT "uq_device_uuid" UNIQUE (uuid);
