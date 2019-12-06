package hospital.dao;

import hospital.model.Prescription;

import java.util.List;

public interface PrescriptionDAO {

    Prescription getPrescriptionById(int id);

    List<Prescription> getAllPrescriptions(int id);

    void updatePrescription(Prescription presc);

    void deletePrescription(Prescription presc);

    void addPresc(Prescription p);
}
