CREATE SCHEMA "common";
CREATE SCHEMA "hydroponic_v1";

ALTER SCHEMA "common" OWNER TO postgres;
ALTER SCHEMA "hydroponic_v1" OWNER TO postgres;

GRANT ALL ON SCHEMA "common" TO postgres;
GRANT ALL ON SCHEMA "hydroponic_v1" TO postgres;
