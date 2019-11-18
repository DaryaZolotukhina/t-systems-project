package hospital.service;

import hospital.dao.PrescriptionDAO;
import hospital.model.Prescription;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService{

    private PrescriptionDAO prescriptionDAO;

    public void setPrescriptionDAO(PrescriptionDAO prescriptionDAO) {
        this.prescriptionDAO = prescriptionDAO;
    }

    @Override
    @Transactional
    public List<Prescription> getAllPrescriptions(int id){
        List<Prescription> prescriptions=prescriptionDAO.getAllPrescriptions(id);
        return prescriptions;
    }

    @Override
    public Prescription getPrescriptionById(int id){
        Prescription prescription = prescriptionDAO.getPrescriptionById(id);
        return prescription;
    }

    @Override
    @Transactional
    public void deletePrescription(Prescription presc){
        prescriptionDAO.deletePrescription(presc);
    }

    @Override
    @Transactional
    public void updateDeletePrescription(Prescription presc) {
        presc.setIsDeleted(true);
        prescriptionDAO.updatePrescription(presc);
    }

}
