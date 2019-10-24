package hospital.service;

import hospital.model.Patient;

import java.util.List;

public interface PatientService {
    Patient getById(Long id);

    void save(Patient patient);

    void delete(Long id);

    List<Patient> getAll();
}
