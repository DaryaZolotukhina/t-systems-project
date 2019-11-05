
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

CREATE TABLE public.doctors
(
    id_doctor SERIAL NOT NULL PRIMARY KEY,
    surname character varying(255) NOT NULL,
    name character varying(255)  NOT NULL,
    patronymic character varying(255)  NOT NULL
);

ALTER TABLE doctors
ADD isDeleted boolean;

CREATE TABLE public.patients
(
    id_patient SERIAL NOT NULL PRIMARY KEY,
    surname character varying(255) NOT NULL,
    name character varying(255)  NOT NULL,
    patronymic character varying(255)  NOT NULL,
    --id_diagnosis REFERENCES diagnosis(id_diagnosis),
    insuranceNum character varying(16) NOT NULL,
	id_doctor integer REFERENCES doctors(id_doctor),
	id_statusPat integer REFERENCES statusPat(id_statusPat)
);

insert into patients values (2,'Ivanov','Ivan','Ivanovich',1234567,1,false,false);
UPDATE patients SET surname = 'Petrov', name='Petr', patronymic='Petrovich',insuranceNum=7654321 where id_patient=1;
select * from patients;

ALTER TABLE patients
ADD isDeleted boolean;

ALTER TABLE patients
ADD isDischarged boolean;

ALTER TABLE patients
DROP COLUMN id_statusPat;

Drop table statusPat;

CREATE TABLE public.patient_diagnosis
(
    id_patient integer REFERENCES patients(id_patient),
	id_diagnosis integer REFERENCES diagnosises(id_diagnosis),
	CONSTRAINT PK_patient_diagnosis PRIMARY KEY(id_patient,id_diagnosis)
);


CREATE TABLE public.procMed
(
    id_procMed SERIAL NOT NULL PRIMARY KEY,
    title character varying(255) NOT NULL,
    id_type integer REFERENCES typeProcMed(id_type)
);

select * from procMed;

update procMed set title='Aspirin';

CREATE TABLE public.typeProcMed
(
	id_type SERIAL NOT NULL PRIMARY KEY,
	title character varying(255) NOT NULL
);

CREATE TABLE public.prescriptions
(
	id_prescription SERIAL NOT NULL PRIMARY KEY,
	id_patient integer REFERENCES patients(id_patient),
	id_procMed integer REFERENCES procMed(id_procMed),
	daySchedule BIT(3) DEFAULT '000',
	weekSchedule BIT(7) DEFAULT '0000000',
	period integer not null,
	dose integer
);

ALTER TABLE prescriptions
ALTER dose TYPE numeric;

    ALTER TABLE prescriptions
        ALTER daySchedule TYPE character varying;

    ALTER TABLE prescriptions
        ALTER weekSchedule TYPE character varying;

ALTER TABLE prescriptions
ADD isDone boolean;

INSERT INTO prescriptions VALUES (2,1,1,'111','0000000',2,2,false);
    INSERT INTO prescriptions VALUES (3,1,1,'111','0000000',2,2,false);
    INSERT INTO prescriptions VALUES (4,2,1,'111','0000000',2,2,false);
select * from prescriptions;

CREATE TABLE public.events
(
	id_patient integer REFERENCES patients(id_patient),
	dateTimeEvent timestamp,
	id_prescription integer REFERENCES prescriptions(id_prescription),
	id_StatusEvent integer REFERENCES StatusEvent(id_StatusEvent),
	CONSTRAINT PK_event PRIMARY KEY(id_patient,dateTimeEvent)
);

CREATE TABLE public.StatusEvent
(
id_StatusEvent SERIAL NOT NULL PRIMARY KEY,
    title character varying(255) NOT NULL
);

drop table events;
	CREATE TABLE public.events
(
	id_event SERIAL primary key,
	id_patient integer REFERENCES patients(id_patient),
	dateTimeEvent timestamp,
	id_prescription integer REFERENCES prescriptions(id_prescription),
	id_procMed integer REFERENCES procMed(id_procMed),
	id_StatusEvent integer REFERENCES StatusEvent(id_StatusEvent)
);
drop table patient_diagnosis;
CREATE TABLE public.patient_diagnosis
(
	id_patient_diagnosis SERIAL PRIMARY KEY,
    id_patient integer REFERENCES patients(id_patient),
	id_diagnosis integer REFERENCES diagnosises(id_diagnosis)
);

select * from events;
select * from prescriptions;
truncate events;

update prescriptions set isDone=false;

update patients set isDeleted=false;