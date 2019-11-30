
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

ALTER TABLE doctors RENAME TO staff;

ALTER TABLE doctors
ADD isDeleted boolean;

    ALTER TABLE staff
        ADD isDoctor boolean;

    ALTER TABLE staff
        RENAME COLUMN id_doctor TO id_staff;
    update staff set isDoctor=true;
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

ALTER TABLE patients  DROP column id_doctor;
ALTER TABLE patients ADD COLUMN id_staff integer references staff(id_staff);
    update patients set id_staff=1;

insert into patients values (2,'Ivanov','Ivan','Ivanovich',1234567,1,false,false);
UPDATE patients SET surname = 'Petrov', name='Petr', patronymic='Petrovich',insuranceNum=7654321 where id_patient=1;
select * from patients;
    insert into patients values (1,'Petrov','Petr','Petrovich',7654321,1,false,false);
insert into doctors values (1,'vd','fds','fsa',false);

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

    ALTER TABLE StatusEvent RENAME TO status_event;

insert into procMed values (2, 'Analgin');
    insert into procMed values (3, 'Nospa');

update procMed set title='Aspirin';

update prescriptions set isDeleted=false;
    update events set isDeleted=false;


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
    ALTER TABLE prescriptions DROP COLUMN daySchedule;
    ALTER TABLE prescriptions
        ALTER daySchedule TYPE character varying;

    ALTER TABLE prescriptions
        ALTER daySchedule TYPE integer USING dayschedule::integer;

    ALTER TABLE prescriptions
        ALTER weekSchedule TYPE character varying;

ALTER TABLE prescriptions
ADD isDone boolean;

    ALTER TABLE prescriptions
        ADD daySchedule integer;

    ALTER TABLE prescriptions
        ADD isDeleted boolean;

    ALTER TABLE events
        ADD isDeleted boolean;
insert into procMed values (1,'Aspirin');
INSERT INTO prescriptions VALUES (2,1,1,'111','0000000',2,2,false);
    INSERT INTO prescriptions VALUES (3,1,1,'111','0000000',2,2,false);
    INSERT INTO prescriptions VALUES (4,2,1,'111','0000000',2,2,false);
select * from prescriptions;

INSERT INTO STAFF VALUES (1,'Semenov','Andrey','Vitalievich',false,1);
    update staff set surname='Andreev', name='Vladimir', patronymic='Vitalievich', isDeleted=false, isDoctor='true'
    where id_staff=1;
    INSERT INTO STAFF VALUES (3,'Danilova','Inna','Semenovna',false,false);
    INSERT INTO STAFF VALUES (4,'Anatoliev','Ivan','Antonovich',false,true);

    update prescriptions set daySchedule=42;

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

insert into Status_Event values (1,'planned');
    insert into Status_Event values (2,'completed');
    insert into Status_Event values (3,'canceled');

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

select * from staff;
select * from patients;
select * from events;
select * from prescriptions;
truncate events;
    truncate events,prescriptions;

    truncate patient_diagnosis,events,prescriptions,patients;

update prescriptions set isDone=false;
    update prescriptions set isDeleted=false;

update patients set isDeleted=false;
update patients set isDischarged=false;

select * from patients;

select * from diagnosis_type;

insert into diagnosis_type values (1,'volchanka');
insert into diagnosis_type values (2,'flu');

insert into procedures values (1, 'injection');
insert into procedures values (2, 'operation');
    insert into procedures values (3, 'dropper');

    insert into medicines values (1, 'aspirin');
    insert into medicines values (2, 'analgin');
    insert into medicines values (3, 'nospa');

insert into diagnosis_type_procedures values (1,1);
    insert into diagnosis_type_procedures values (3,1);
    insert into diagnosis_type_procedures values (2,2);

    insert into diagnosis_type_medicines values (1,1);
    insert into diagnosis_type_medicines values (3,1);
    insert into diagnosis_type_medicines values (2,2);

select * from diagnosises;
truncate events, prescriptions, diagnosises;
select * from procedures;

insert into staff_type values (1,'immunologist');
