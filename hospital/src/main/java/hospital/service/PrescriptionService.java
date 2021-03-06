package hospital.service;

import hospital.model.Prescription;

import java.util.List;

public interface PrescriptionService {
    List<Prescription> getAllPrescriptions(int id);

    Prescription getPrescriptionById(int id);

    void deletePrescription(Prescription presc);

    void updateDeletePrescription(Prescription presc);

}
