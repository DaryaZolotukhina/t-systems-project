package hospital.service;

import hospital.model.Patient;
import hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository patientRepository;

    public Patient getById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public void save(Patient patient) {
        patientRepository.save(patient);
    }

    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }
}
