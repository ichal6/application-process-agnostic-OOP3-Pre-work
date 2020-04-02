DROP TABLE IF EXISTS mentors;

CREATE TABLE mentors (
                         id SERIAL PRIMARY KEY,
                         first_name character varying(255) NOT NULL,
                         last_name character varying(255) NOT NULL,
                         nick_name character varying(255),
                         phone_number character varying(100) NOT NULL,
                         email character varying(255) NOT NULL,
                         city character varying(255) NOT NULL,
                         favourite_number integer
);