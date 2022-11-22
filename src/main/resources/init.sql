CREATE SCHEMA IF NOT EXISTS "hydroponic_v1";

CREATE TABLE IF NOT EXISTS public."device"
(
    "id"                        bigserial PRIMARY KEY,
    "name"                      text,
    "description"               text,
    "type"                      text,
    "uuid"                      text UNIQUE,
    "owner_id"                  bigint,
    "delegated_accounts_number" int,
    "delegatable"               bool,
    "repaired_amount_number"    int,
    "created_timestamp"         timestamp with time zone,
    "last_repaired_timestamp"   timestamp with time zone
);

CREATE TABLE IF NOT EXISTS public."device_delegate_secret"
(
    "secret"     text,
    "account_id" bigint,
    "device_id"  bigint PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS public."device_details"
(
    "version"                text,
    "modification"           text,
    "manufactured_timestamp" timestamp with time zone,
    "manufactured_country"   text,
    "sold_timestamp"         timestamp with time zone,
    "sold_country"           text,
    "warranty_from"          timestamp with time zone,
    "warranty_to"            timestamp with time zone,
    "device_id"              bigint PRIMARY KEY
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

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."dispenser_settings_template"
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

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."dispenser_settings"
(
    "id"                             bigserial PRIMARY KEY,
    "device_uuid"                    text,
    "dispenser_settings_template_id" bigint,
    "updated_timestamp"              timestamp with time zone
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
    "id"                            bigserial PRIMARY KEY,
    "device_uuid"                   text,
    "dispense_schedule_template_id" bigint,
    "updated_timestamp"             timestamp with time zone
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

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."ec_sensor_settings_template"
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

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."ec_sensor_settings"
(
    "id"                             bigserial PRIMARY KEY,
    "device_uuid"                    text,
    "ec_sensor_settings_template_id" bigint,
    "updated_timestamp"              timestamp with time zone
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."ph_sensor_settings_template"
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

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."ph_sensor_settings"
(
    "id"                             bigserial PRIMARY KEY,
    "device_uuid"                    text,
    "ph_sensor_settings_template_id" bigint,
    "updated_timestamp"              timestamp with time zone
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."system_settings_template"
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

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."system_settings"
(
    "id"                          bigserial PRIMARY KEY,
    "device_uuid"                 text,
    "system_settings_template_id" bigint,
    "updated_timestamp"           timestamp with time zone
);

CREATE TABLE IF NOT EXISTS "hydroponic_v1"."special_system_settings"
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


ALTER TABLE "device_delegate_secret"
    ADD FOREIGN KEY ("device_id") REFERENCES public."device" ("id");

ALTER TABLE "device_details"
    ADD FOREIGN KEY ("device_id") REFERENCES public."device" ("id");

ALTER TABLE "hydroponic_v1"."data"
    ADD FOREIGN KEY ("device_uuid") REFERENCES public."device" ("uuid");

ALTER TABLE "hydroponic_v1"."dispenser_settings"
    ADD FOREIGN KEY ("device_uuid") REFERENCES public."device" ("uuid");

ALTER TABLE "hydroponic_v1"."dispenser_settings"
    ADD FOREIGN KEY ("dispenser_settings_template_id") REFERENCES "hydroponic_v1"."dispenser_settings_template" ("id");

ALTER TABLE "hydroponic_v1"."dispense_schedule"
    ADD FOREIGN KEY ("device_uuid") REFERENCES public."device" ("uuid");

ALTER TABLE "hydroponic_v1"."dispense_schedule"
    ADD FOREIGN KEY ("dispense_schedule_template_id") REFERENCES "hydroponic_v1"."dispense_schedule_template" ("id");

ALTER TABLE "hydroponic_v1"."dispense_schedule_element"
    ADD FOREIGN KEY ("dispense_schedule_template_id") REFERENCES "hydroponic_v1"."dispense_schedule_template" ("id");

ALTER TABLE "hydroponic_v1"."dispenser_scheduling"
    ADD FOREIGN KEY ("dispense_schedule_element_id") REFERENCES "hydroponic_v1"."dispense_schedule_element" ("id");

ALTER TABLE "hydroponic_v1"."ec_sensor_settings"
    ADD FOREIGN KEY ("device_uuid") REFERENCES public."device" ("uuid");

ALTER TABLE "hydroponic_v1"."ec_sensor_settings"
    ADD FOREIGN KEY ("ec_sensor_settings_template_id") REFERENCES "hydroponic_v1"."ec_sensor_settings_template" ("id");

ALTER TABLE "hydroponic_v1"."ph_sensor_settings"
    ADD FOREIGN KEY ("device_uuid") REFERENCES public."device" ("uuid");

ALTER TABLE "hydroponic_v1"."ph_sensor_settings"
    ADD FOREIGN KEY ("ph_sensor_settings_template_id") REFERENCES "hydroponic_v1"."ph_sensor_settings_template" ("id");

ALTER TABLE "hydroponic_v1"."system_settings"
    ADD FOREIGN KEY ("device_uuid") REFERENCES public."device" ("uuid");

ALTER TABLE "hydroponic_v1"."system_settings"
    ADD FOREIGN KEY ("system_settings_template_id") REFERENCES "hydroponic_v1"."system_settings_template" ("id");

ALTER TABLE "hydroponic_v1"."special_system_settings"
    ADD FOREIGN KEY ("device_uuid") REFERENCES public."device" ("uuid");