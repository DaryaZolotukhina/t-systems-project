create table Specializations
(
    id_specialization SERIAL NOT NULL PRIMARY KEY,
    title character varying(255) NOT NULL
);

create table Specialization_procedures
(
    id_procedure integer NOT NULL,
    id_specialization integer NOT NULL,
    PRIMARY KEY (id_procedure,id_specialization),
    CONSTRAINT specialization_procedures_ibfk
   FOREIGN KEY (id_specialization) REFERENCES specializations (id_specialization),
  CONSTRAINT id_procedure_ibfk_7
   FOREIGN KEY (id_procedure) REFERENCES Procedures (id_procedure)
);

create table staff_type(
    id_staff_type SERIAL NOT NULL PRIMARY KEY,
    title character varying(255) NOT NULL
);

alter table staff drop column isDoctor;

alter table staff add column
id_staff_type integer REFERENCES staff_type(id_staff_type);