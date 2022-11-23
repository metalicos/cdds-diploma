CREATE TABLE "hydroponic_v1"."dispenser_template"
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

ALTER TABLE "hydroponic_v1"."dispenser_template" OWNER to postgres;