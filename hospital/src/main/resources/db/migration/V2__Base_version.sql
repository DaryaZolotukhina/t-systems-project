alter table ProcMed RENAME TO Medicines;

alter table Medicines drop column id_type;

alter table Medicines RENAME COLUMN id_procMed TO id_medicine;

drop table typeProcMed;

create table Procedures
(
    id_procedure SERIAL NOT NULL PRIMARY KEY,
    title character varying(255) NOT NULL
);


create table diagnosis_type
(
    id_diagnosis_type SERIAL NOT NULL PRIMARY KEY,
    title character varying(255) NOT NULL

);

create table diagnosis_type_procedures
(
    id_procedure integer NOT NULL,
    id_diagnosis_type integer NOT NULL,
    PRIMARY KEY (id_procedure,id_diagnosis_type),
    CONSTRAINT diagnosis_type_procedures_ibfk_1
   FOREIGN KEY (id_diagnosis_type) REFERENCES diagnosis_type (id_diagnosis_type),
  CONSTRAINT id_procedure_ibfk_2
   FOREIGN KEY (id_procedure) REFERENCES Procedures (id_procedure)
);

create table diagnosis_type_medicines
(
    id_medicine integer NOT NULL,
    id_diagnosis_type integer NOT NULL,
    PRIMARY KEY (id_medicine,id_diagnosis_type),
    CONSTRAINT diagnosis_type_medicine_ibfk_3
   FOREIGN KEY (id_diagnosis_type) REFERENCES diagnosis_type (id_diagnosis_type),
  CONSTRAINT id_medicine_ibfk_4
   FOREIGN KEY (id_medicine) REFERENCES Medicines (id_medicine)
);

drop table patient_diagnosis;

CREATE TABLE patient_diagnosis
(
    id_patient integer NOT NULL,
	id_diagnosis integer NOT NULL,
	PRIMARY KEY (id_patient,id_diagnosis),
	CONSTRAINT patient_diagnosis_ibfk_5
   FOREIGN KEY (id_patient) REFERENCES patients(id_patient),
  CONSTRAINT id_diagnosis_ibfk_6
   FOREIGN KEY (id_diagnosis) REFERENCES diagnosises(id_diagnosis)
);

alter table diagnosises add column
id_diagnosis_type integer REFERENCES diagnosis_type(id_diagnosis_type);
