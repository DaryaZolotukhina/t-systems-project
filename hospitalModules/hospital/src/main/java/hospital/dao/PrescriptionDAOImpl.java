package hospital.dao;

import hospital.model.Patient;
import hospital.model.Prescription;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class PrescriptionDAOImpl implements PrescriptionDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public void deletePrescription(Prescription presc) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(presc);
    }

    @Override
    public Prescription getPrescriptionById(BigInteger id){
        Session session = sessionFactory.getCurrentSession();
        Prescription prescription = (Prescription) session.load(Prescription.class, id);
        return prescription;
    }

    @Override
    public List<Prescription> getAllPrescriptions(BigInteger id){
        Session session = sessionFactory.getCurrentSession();
        Patient p = (Patient) session.load(Patient.class,id);
        List<Prescription> prescriptions=p.getPrescriptions();
        return prescriptions;
    }

    @Override
    public void addPresc(Prescription p) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(p);
    }

    @Override
    public void updatePrescription(Prescription presc) {
        Session session = sessionFactory.getCurrentSession();
        session.update(presc);
    }
}
