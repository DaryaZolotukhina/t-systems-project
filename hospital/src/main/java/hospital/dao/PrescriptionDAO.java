package hospital.dao;

import hospital.model.Prescription;

import java.util.List;

public interface PrescriptionDAO {
    void updatePrescription(Prescription presc);

    Prescription getPrescriptionById(int id);

    List<Prescription> getAllPrescriptions(int id);
}
