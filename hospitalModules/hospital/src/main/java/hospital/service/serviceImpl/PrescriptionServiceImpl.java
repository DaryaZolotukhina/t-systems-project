package hospital.service.serviceImpl;

import hospital.dao.PrescriptionDAO;
import hospital.dto.prescription.PrescriptionDto;
import hospital.mappers.PrescriptionMapper;
import hospital.model.Prescription;
import hospital.service.PrescriptionService;
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
        return prescriptionDAO.getAllPrescriptions(id);
    }

    @Override
    public PrescriptionDto getPrescriptionById(int id){
        Prescription prescription = prescriptionDAO.getPrescriptionById(id);
        return PrescriptionMapper.PRESCRIPTION_MAPPER.fromPrescription(prescription);
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
