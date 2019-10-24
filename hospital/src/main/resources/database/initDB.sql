DROP TABLE public.patients;
CREATE TABLE public.patients
(
    id SERIAL NOT NULL ,
    surname character varying(255) NOT NULL,
    name character varying(255)  NOT NULL,
    patronymic character varying(255)  NOT NULL,
    diagnosis character varying(255)  NOT NULL,
    CONSTRAINT patients_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.patients
    OWNER to postgres;