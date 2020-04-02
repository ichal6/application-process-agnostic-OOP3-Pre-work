DROP TABLE IF EXISTS applicants;

CREATE TABLE applicants (
                            id SERIAL PRIMARY KEY,
                            first_name character varying(255) NOT NULL,
                            last_name character varying(255) NOT NULL,
                            phone_number character varying(100) NOT NULL,
                            email character varying(255) NOT NULL,
                            application_code integer UNIQUE NOT NULL
);