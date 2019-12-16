package hospital.dao;

import hospital.model.Prescription;

import java.math.BigInteger;
import java.util.List;

public interface PrescriptionDAO {

    Prescription getPrescriptionById(BigInteger id);

    List<Prescription> getAllPrescriptions(BigInteger id);

    void updatePrescription(Prescription presc);

    void deletePrescription(Prescription presc);

    void addPresc(Prescription p);
}
