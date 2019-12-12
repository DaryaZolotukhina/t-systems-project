alter table prescriptions add column
id_staff integer REFERENCES staff(id_staff);