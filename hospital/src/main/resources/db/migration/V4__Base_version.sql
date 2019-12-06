ALTER TABLE EVENTS DROP COLUMN id_procMed;
ALTER TABLE EVENTS ADD COLUMN id_medicine integer REFERENCES medicines(id_medicine);
ALTER TABLE EVENTS ADD COLUMN id_procedure integer REFERENCES procedures(id_procedure);
ALTER TABLE PRESCRIPTIONS DROP COLUMN id_procMed;
ALTER TABLE PRESCRIPTIONS ADD COLUMN id_medicine integer REFERENCES medicines(id_medicine);
ALTER TABLE PRESCRIPTIONS ADD COLUMN id_procedure integer REFERENCES procedures(id_procedure);