package hospital.service;

import hospital.dto.PrescriptionDto;
import hospital.model.Prescription;

import java.util.List;

public interface PrescriptionService {
    List<Prescription> getAllPrescriptions(int id);

    PrescriptionDto getPrescriptionById(int id);

    void deletePrescription(Prescription presc);

    void updateDeletePrescription(Prescription presc);

}
