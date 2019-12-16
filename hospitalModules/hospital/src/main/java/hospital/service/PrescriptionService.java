package hospital.service;

import hospital.dto.prescription.PrescriptionDto;
import hospital.model.Prescription;

import java.math.BigInteger;
import java.util.List;

public interface PrescriptionService {
    PrescriptionDto getPrescriptionById(BigInteger id);

    List<Prescription> getAllPrescriptions(BigInteger id);

    void deletePrescription(Prescription presc);

    void updateDeletePrescription(Prescription presc);

}
