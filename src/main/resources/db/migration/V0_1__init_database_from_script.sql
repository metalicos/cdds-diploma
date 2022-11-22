CREATE SCHEMA IF NOT EXISTS "common";

CREATE SCHEMA IF NOT EXISTS "hydroponic_v1";

CREATE TABLE IF NOT EXISTS "common"."device"
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

CREATE TABLE IF NOT EXISTS "common"."device_delegate_secret"
(
    "id"          bigserial PRIMARY KEY,
    "secret"      text   NOT NULL,
    "account_id"  bigint NOT NULL,
    "device_uuid" text
);

CREATE TABLE IF NOT EXISTS "common"."device_details"
(
    "id"                     bigserial PRIMARY KEY,
    "version"                text,
    "modification"           text,
    "manufactured_timestamp" timestamp with time zone,
    "manufactured_country"   text,
    "sold_timestamp"         timestamp with time zone,
    "sold_country"           text,
    "warranty_from"          timestamp with time zone,
    "warranty_to"            timestamp with time zone,
    "device_uuid"            text
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."data"
(
    "id"                   bigserial PRIMARY KEY,
    "device_uuid"          text,
    "ec_solution"          double precision,
    "ph_solution"          double precision,
    "t_solution"           double precision,
    "t_air"                double precision,
    "humidity_air"         double precision,
    "atmospheric_pressure" double precision,
    "created_timestamp"    timestamp with time zone,
    "updated_timestamp"    timestamp with time zone
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."dispenser_template"
(
    "id"                              bigserial PRIMARY KEY,
    "name"                            text,
    "description"                     text,
    "owner_id"                        bigint,
    "index"                           int,
    "time"                            timestamp with time zone,
    "dispenser_name"                  text,
    "pin_a"                           int,
    "pin_b"                           int,
    "regulation_direction"            bool,
    "enable"                          bool,
    "polarity"                        bool,
    "smart_dose"                      bool,
    "total_added_ml"                  double precision,
    "ml_per_ms"                       double precision,
    "target_value"                    double precision,
    "error"                           double precision,
    "recheck_dispenser_after_seconds" bigint,
    "last_dispenser_recheck_time"     bigint,
    "mixing_volume_ml"                int,
    "updated_timestamp"               timestamp with time zone
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."dispenser"
(
    "id"                bigserial PRIMARY KEY,
    "device_uuid"       text,
    "updated_timestamp" timestamp with time zone
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."dispenser_dispenser_template"
(
    "id"                    bigserial PRIMARY KEY,
    "dispenser_id"          bigint,
    "dispenser_template_id" bigint
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."dispense_schedule_template"
(
    "id"                bigserial PRIMARY KEY,
    "name"              text,
    "description"       text,
    "owner_id"          bigint,
    "created_timestamp" timestamp with time zone,
    "updated_timestamp" timestamp with time zone
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."dispense_schedule"
(
    "id"                bigserial PRIMARY KEY,
    "device_uuid"       text,
    "updated_timestamp" timestamp with time zone
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."dispense_schedule_dispense_schedule_template"
(
    "id"                            bigserial PRIMARY KEY,
    "dispense_schedule_id"          bigint,
    "dispense_schedule_template_id" bigint
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."dispense_schedule_element"
(
    "id"                            bigserial PRIMARY KEY,
    "dispense_schedule_template_id" bigint,
    "index"                         int,
    "time"                          timestamp with time zone,
    "day"                           int,
    "target_ec"                     double precision,
    "target_ph"                     double precision,
    "ec_error"                      double precision,
    "ph_error"                      double precision,
    "recheck_after_sec"             bigint,
    "created_timestamp"             timestamp with time zone,
    "updated_timestamp"             timestamp with time zone
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."dispenser_scheduling"
(
    "id"                           bigserial PRIMARY KEY,
    "index"                        int,
    "label"                        text,
    "dose_ml"                      double precision,
    "is_active"                    bool,
    "dispense_schedule_element_id" bigint,
    "created_timestamp"            timestamp with time zone,
    "updated_timestamp"            timestamp with time zone
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."ec_sensor_template"
(
    "id"                bigserial PRIMARY KEY,
    "name"              text,
    "description"       text,
    "owner_id"          bigint,
    "time"              timestamp with time zone,
    "k_low_point"       double precision,
    "k_high_point"      double precision,
    "raw_ec"            double precision,
    "created_timestamp" timestamp with time zone,
    "updated_timestamp" timestamp with time zone
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."ec_sensor_ec_sensor_template"
(
    "id"                    bigserial PRIMARY KEY,
    "ec_sensor_id"          bigint,
    "ec_sensor_template_id" bigint
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."ec_sensor"
(
    "id"                bigserial PRIMARY KEY,
    "device_uuid"       text,
    "updated_timestamp" timestamp with time zone
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."ph_sensor_template"
(
    "id"                bigserial PRIMARY KEY,
    "name"              text,
    "description"       text,
    "owner_id"          bigint,
    "time"              timestamp with time zone,
    "point"             double precision,
    "value"             double precision[],
    "adc"               int[],
    "slope"             double precision,
    "adc_offset"        int,
    "oversampling"      int,
    "created_timestamp" timestamp with time zone,
    "updated_timestamp" timestamp with time zone
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."ph_sensor"
(
    "id"                bigserial PRIMARY KEY,
    "device_uuid"       text,
    "updated_timestamp" timestamp with time zone
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."ph_sensor_ph_sensor_template"
(
    "id"                    bigserial PRIMARY KEY,
    "ph_sensor_template_id" bigint,
    "ph_sensor_id"          bigint
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."system_template"
(
    "id"                bigserial PRIMARY KEY,
    "name"              text,
    "description"       text,
    "owner_id"          bigint,
    "time"              timestamp with time zone,
    "time_zone"         text,
    "ntp_server"        text,
    "wifi_ssid"         text,
    "wifi_pass"         text,
    "restarts"          bigint,
    "growing_day"       int,
    "is_growing"        boolean,
    "grow_start_date"   bigint,
    "dispensers_enable" boolean,
    "sensors_enable"    boolean,
    "created_timestamp" timestamp with time zone,
    "updated_timestamp" timestamp with time zone
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."system"
(
    "id"                bigserial PRIMARY KEY,
    "device_uuid"       text,
    "updated_timestamp" timestamp with time zone
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."system_system_template"
(
    "id"                 bigserial PRIMARY KEY,
    "system_template_id" bigint,
    "system_id"          bigint
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."special_system"
(
    "id"                bigserial PRIMARY KEY,
    "device_uuid"       text,
    "time"              timestamp with time zone,
    "software_version"  text,
    "schedule_amount"   int,
    "dispensers_amount" int,
    "wifi_rssi"         text,
    "wifi_bsid"         text,
    "local_ip"          text,
    "subnet_mask"       text,
    "gateway_ip"        text,
    "mac_addr"          text,
    "created_timestamp" timestamp with time zone,
    "updated_timestamp" timestamp with time zone
);

ALTER TABLE "common"."device"
    DROP CONSTRAINT IF EXISTS "uq_device_uuid" CASCADE;
ALTER TABLE "common"."device"
    ADD CONSTRAINT "uq_device_uuid" UNIQUE (uuid);

ALTER TABLE "common"."device_delegate_secret"
    DROP CONSTRAINT IF EXISTS "fk_device_delegate_secret_device_uuid" CASCADE;
ALTER TABLE "common"."device_delegate_secret"
    ADD CONSTRAINT "fk_device_delegate_secret_device_uuid" FOREIGN KEY ("device_uuid") REFERENCES "common"."device" ("uuid");

ALTER TABLE "common"."device_details"
    DROP CONSTRAINT IF EXISTS "fk_device_details_device_uuid" CASCADE;
ALTER TABLE "common"."device_details"
    ADD CONSTRAINT "fk_device_details_device_uuid" FOREIGN KEY ("device_uuid") REFERENCES "common"."device" ("uuid");

ALTER TABLE "hydroponic_v1"."data"
    DROP CONSTRAINT IF EXISTS "fk_data_device_uuid" CASCADE;
ALTER TABLE "hydroponic_v1"."data"
    ADD CONSTRAINT "fk_data_device_uuid" FOREIGN KEY ("device_uuid") REFERENCES "common"."device" ("uuid");

ALTER TABLE "hydroponic_v1"."dispenser"
    DROP CONSTRAINT IF EXISTS "fk_dispenser_device_uuid" CASCADE;
ALTER TABLE "hydroponic_v1"."dispenser"
    ADD CONSTRAINT "fk_dispenser_device_uuid" FOREIGN KEY ("device_uuid") REFERENCES "common"."device" ("uuid");

ALTER TABLE "hydroponic_v1"."dispenser_dispenser_template"
    DROP CONSTRAINT IF EXISTS "fk_dispenser_dispenser_template_dispenser_id" CASCADE;
ALTER TABLE "hydroponic_v1"."dispenser_dispenser_template"
    ADD CONSTRAINT "fk_dispenser_dispenser_template_dispenser_id" FOREIGN KEY ("dispenser_id") REFERENCES "hydroponic_v1"."dispenser" ("id");

ALTER TABLE "hydroponic_v1"."dispenser_dispenser_template"
    DROP CONSTRAINT IF EXISTS "fk_dispenser_dispenser_template_dispenser_template_id" CASCADE;
ALTER TABLE "hydroponic_v1"."dispenser_dispenser_template"
    ADD CONSTRAINT "fk_dispenser_dispenser_template_dispenser_template_id" FOREIGN KEY ("dispenser_template_id") REFERENCES "hydroponic_v1"."dispenser_template" ("id");

ALTER TABLE "hydroponic_v1"."dispense_schedule"
    DROP CONSTRAINT IF EXISTS "fk_dispense_schedule_device_uuid" CASCADE;
ALTER TABLE "hydroponic_v1"."dispense_schedule"
    ADD CONSTRAINT "fk_dispense_schedule_device_uuid" FOREIGN KEY ("device_uuid") REFERENCES "common"."device" ("uuid");

ALTER TABLE "hydroponic_v1"."dispense_schedule_dispense_schedule_template"
    DROP CONSTRAINT IF EXISTS "fk_disp_schedule_disp_schedule_templ_dispense_schedule_id" CASCADE;
ALTER TABLE "hydroponic_v1"."dispense_schedule_dispense_schedule_template"
    ADD CONSTRAINT "fk_disp_schedule_disp_schedule_templ_dispense_schedule_id" FOREIGN KEY ("dispense_schedule_id") REFERENCES "hydroponic_v1"."dispense_schedule" ("id");

ALTER TABLE "hydroponic_v1"."dispense_schedule_dispense_schedule_template"
    DROP CONSTRAINT IF EXISTS "fk_disp_schedule_disp_schedule_templ_disp_schedule_template_id" CASCADE;
ALTER TABLE "hydroponic_v1"."dispense_schedule_dispense_schedule_template"
    ADD CONSTRAINT "fk_disp_schedule_disp_schedule_templ_disp_schedule_template_id" FOREIGN KEY ("dispense_schedule_template_id") REFERENCES "hydroponic_v1"."dispense_schedule_template" ("id");

ALTER TABLE "hydroponic_v1"."dispense_schedule_element"
    DROP CONSTRAINT IF EXISTS "fk_dispense_schedule_element_dispense_schedule_template_id" CASCADE;
ALTER TABLE "hydroponic_v1"."dispense_schedule_element"
    ADD CONSTRAINT "fk_dispense_schedule_element_dispense_schedule_template_id" FOREIGN KEY ("dispense_schedule_template_id") REFERENCES "hydroponic_v1"."dispense_schedule_template" ("id");

ALTER TABLE "hydroponic_v1"."dispenser_scheduling"
    DROP CONSTRAINT IF EXISTS "fk_dispenser_scheduling_dispense_schedule_element_id" CASCADE;
ALTER TABLE "hydroponic_v1"."dispenser_scheduling"
    ADD CONSTRAINT "fk_dispenser_scheduling_dispense_schedule_element_id" FOREIGN KEY ("dispense_schedule_element_id") REFERENCES "hydroponic_v1"."dispense_schedule_element" ("id");

ALTER TABLE "hydroponic_v1"."ec_sensor_ec_sensor_template"
    DROP CONSTRAINT IF EXISTS "fk_ec_sensor_ec_sensor_template_ec_sensor_id" CASCADE;
ALTER TABLE "hydroponic_v1"."ec_sensor_ec_sensor_template"
    ADD CONSTRAINT "fk_ec_sensor_ec_sensor_template_ec_sensor_id" FOREIGN KEY ("ec_sensor_id") REFERENCES "hydroponic_v1"."ec_sensor" ("id");

ALTER TABLE "hydroponic_v1"."ec_sensor_ec_sensor_template"
    DROP CONSTRAINT IF EXISTS "fk_ec_sensor_ec_sensor_template_ec_sensor_template_id" CASCADE;
ALTER TABLE "hydroponic_v1"."ec_sensor_ec_sensor_template"
    ADD CONSTRAINT "fk_ec_sensor_ec_sensor_template_ec_sensor_template_id" FOREIGN KEY ("ec_sensor_template_id") REFERENCES "hydroponic_v1"."ec_sensor_template" ("id");

ALTER TABLE "hydroponic_v1"."ec_sensor"
    DROP CONSTRAINT IF EXISTS "fk_ec_sensor_device_uuid" CASCADE;
ALTER TABLE "hydroponic_v1"."ec_sensor"
    ADD CONSTRAINT "fk_ec_sensor_device_uuid" FOREIGN KEY ("device_uuid") REFERENCES "common"."device" ("uuid");

ALTER TABLE "hydroponic_v1"."ph_sensor"
    DROP CONSTRAINT IF EXISTS "fk_ph_sensor_device_uuid" CASCADE;
ALTER TABLE "hydroponic_v1"."ph_sensor"
    ADD CONSTRAINT "fk_ph_sensor_device_uuid" FOREIGN KEY ("device_uuid") REFERENCES "common"."device" ("uuid");

ALTER TABLE "hydroponic_v1"."ph_sensor_ph_sensor_template"
    DROP CONSTRAINT IF EXISTS "fk_ph_sensor_ph_sensor_template_ph_sensor_template_id" CASCADE;
ALTER TABLE "hydroponic_v1"."ph_sensor_ph_sensor_template"
    ADD CONSTRAINT "fk_ph_sensor_ph_sensor_template_ph_sensor_template_id" FOREIGN KEY ("ph_sensor_template_id") REFERENCES "hydroponic_v1"."ph_sensor_template" ("id");

ALTER TABLE "hydroponic_v1"."ph_sensor_ph_sensor_template"
    DROP CONSTRAINT IF EXISTS "fk_ph_sensor_ph_sensor_template_ph_sensor_id" CASCADE;
ALTER TABLE "hydroponic_v1"."ph_sensor_ph_sensor_template"
    ADD CONSTRAINT "fk_ph_sensor_ph_sensor_template_ph_sensor_id" FOREIGN KEY ("ph_sensor_id") REFERENCES "hydroponic_v1"."ph_sensor" ("id");

ALTER TABLE "hydroponic_v1"."system"
    DROP CONSTRAINT IF EXISTS "fk_system_device_uuid" CASCADE;
ALTER TABLE "hydroponic_v1"."system"
    ADD CONSTRAINT "fk_system_device_uuid" FOREIGN KEY ("device_uuid") REFERENCES "common"."device" ("uuid");

ALTER TABLE "hydroponic_v1"."system_system_template"
    DROP CONSTRAINT IF EXISTS "fk_system_system_template_system_template_id" CASCADE;
ALTER TABLE "hydroponic_v1"."system_system_template"
    ADD CONSTRAINT "fk_system_system_template_system_template_id" FOREIGN KEY ("system_template_id") REFERENCES "hydroponic_v1"."system_template" ("id");

ALTER TABLE "hydroponic_v1"."system_system_template"
    DROP CONSTRAINT IF EXISTS "fk_system_system_template_system_id" CASCADE;
ALTER TABLE "hydroponic_v1"."system_system_template"
    ADD CONSTRAINT "fk_system_system_template_system_id" FOREIGN KEY ("system_id") REFERENCES "hydroponic_v1"."system" ("id");

ALTER TABLE "hydroponic_v1"."special_system"
    DROP CONSTRAINT IF EXISTS "fk_special_system_device_uuid" CASCADE;
ALTER TABLE "hydroponic_v1"."special_system"
    ADD CONSTRAINT "fk_special_system_device_uuid" FOREIGN KEY ("device_uuid") REFERENCES "common"."device" ("uuid");
