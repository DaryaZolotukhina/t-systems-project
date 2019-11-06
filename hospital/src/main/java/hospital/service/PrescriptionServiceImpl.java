package hospital.service;

import hospital.dao.PatientDAO;
import hospital.dao.PrescriptionDAO;
import hospital.model.Prescription;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private PrescriptionDAO prescriptionDAO;

    public void setPrescriptionDAO(PrescriptionDAO prescriptionDAO) {
        this.prescriptionDAO = prescriptionDAO;
    }

    @Override
    @Transactional
    public List<Prescription> getAllPrescriptions(int id){
        List<Prescription> prescriptions=this.prescriptionDAO.getAllPrescriptions(id);
        return prescriptions;
    }

    @Override
    @Transactional
    public Prescription getPrescriptionById(int id){
        Prescription prescription = this.prescriptionDAO.getPrescriptionById(id);
        return prescription;
    }

    @Override
    @Transactional
    public void updatePrescription(Prescription presc) {
       this.prescriptionDAO.updatePrescription(presc);
    }
}
