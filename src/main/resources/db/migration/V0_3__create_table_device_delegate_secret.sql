CREATE TABLE "common"."device_delegate_secret"
(
    "id"          bigserial PRIMARY KEY,
    "secret"      text   NOT NULL,
    "account_id"  bigint NOT NULL,
    "device_uuid" text
);

ALTER TABLE "common"."device_delegate_secret" OWNER to postgres;

ALTER TABLE "common"."device_delegate_secret"
    ADD CONSTRAINT "fk_device_delegate_secret_device_uuid" FOREIGN KEY ("device_uuid")
        REFERENCES "common"."device" ("uuid");