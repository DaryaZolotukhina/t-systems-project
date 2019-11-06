package hospital.dao;

import hospital.model.Patient;
import hospital.model.Prescription;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PrescriptionDAOImpl implements PrescriptionDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public void updatePrescription(Prescription presc) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(presc);
    }

    @Override
    public Prescription getPrescriptionById(int id){
        Session session = this.sessionFactory.getCurrentSession();
        Prescription prescription = (Prescription) session.load(Prescription.class, new Integer(id));
        return prescription;
    }

    @Override
    public List<Prescription> getAllPrescriptions(int id){
        Session session = this.sessionFactory.getCurrentSession();
        Patient p = (Patient) session.load(Patient.class, new Integer(id));
        List<Prescription> prescriptions=p.getPrescriptions();
        return prescriptions;
    }
}
