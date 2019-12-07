CREATE TABLE public.diagnosises
(
    id_diagnosis SERIAL NOT NULL PRIMARY KEY,
    title character varying(255) NOT NULL
);

CREATE TABLE public.statusPat
(
    id_statusPat SERIAL NOT NULL PRIMARY KEY,
    title character varying(255) NOT NULL
);

CREATE TABLE public.staff
(
    id_staff SERIAL NOT NULL PRIMARY KEY,
    surname character varying(255) NOT NULL,
    name character varying(255)  NOT NULL,
    patronymic character varying(255)  NOT NULL,
    isDeleted boolean,
    isDoctor boolean
);

CREATE TABLE public.patients
(
    id_patient SERIAL NOT NULL PRIMARY KEY,
    surname character varying(255) NOT NULL,
    name character varying(255)  NOT NULL,
    patronymic character varying(255)  NOT NULL,
    insuranceNum character varying(16) NOT NULL,
	id_staff integer references staff(id_staff)
);

CREATE TABLE public.patient_diagnosis
(
    id_patient integer REFERENCES patients(id_patient),
	id_diagnosis integer REFERENCES diagnosises(id_diagnosis),
	CONSTRAINT PK_patient_diagnosis PRIMARY KEY(id_patient,id_diagnosis)
);


CREATE TABLE public.typeProcMed
(
    id_type SERIAL NOT NULL PRIMARY KEY,
    title character varying(255) NOT NULL
);

CREATE TABLE public.procMed
(
    id_procMed SERIAL NOT NULL PRIMARY KEY,
    title character varying(255) NOT NULL,
    id_type integer REFERENCES typeProcMed(id_type)
);

CREATE TABLE public.prescriptions
(
	id_prescription SERIAL NOT NULL PRIMARY KEY,
	id_patient integer REFERENCES patients(id_patient),
	id_procMed integer REFERENCES procMed(id_procMed),
	daySchedule integer,
	weekSchedule integer,
	period integer not null,
	dose numeric,
	isDone boolean,
	isDeleted boolean

);

CREATE TABLE public.status_event
(
    id_StatusEvent SERIAL NOT NULL PRIMARY KEY,
    title character varying(255) NOT NULL
);

CREATE TABLE public.events
(
	id_event SERIAL primary key,
	id_patient integer REFERENCES patients(id_patient),
	dateTimeEvent timestamp,
	id_prescription integer REFERENCES prescriptions(id_prescription),
	id_procMed integer REFERENCES procMed(id_procMed),
	id_StatusEvent integer REFERENCES Status_Event(id_StatusEvent),
	isDeleted boolean
);

