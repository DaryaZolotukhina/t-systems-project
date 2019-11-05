package hospital.dao;

import java.util.List;

import hospital.model.Event;
import hospital.model.Patient;
import hospital.model.Prescription;
import hospital.model.StatusEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;


@Repository
public class PatientDAOImpl implements PatientDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addPatient(Patient p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
	}

	@Override
	public void updatePatient(Patient p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
	}

	@Override
	public void updatePrescription(Prescription presc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(presc);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Patient> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Patient> patientsList = session.createQuery("from Patient").list();
		return patientsList;
	}

	@Override
	public Patient getById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Patient p = (Patient) session.load(Patient.class, new Integer(id));
		return p;
	}

	@Override
	public void delete(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Patient p = (Patient) session.load(Patient.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
	}

	@Override
	public StatusEvent getStatusEventById(int id){
		Session session = this.sessionFactory.getCurrentSession();
		StatusEvent statusEvent = (StatusEvent) session.load(StatusEvent.class, new Integer(id));
		return statusEvent;
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

	@Override
	public List<Event> getAllEvents(int id){
		Session session = this.sessionFactory.getCurrentSession();
		Patient p = (Patient) session.load(Patient.class, new Integer(id));
		List<Event> events=p.getEvents();
		return events;
	}


	@Override
	public void saveEvent(Event event) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(event);
	}
}
