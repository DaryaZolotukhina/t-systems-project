package hospital.dao;

import java.util.List;

import hospital.model.*;
import hospital.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


@Repository
public class PatientDAOImpl implements PatientDAO {

	private static final EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("hospital");
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

	@Override
	public List<Patient> getPatientsByPage(int pageid, int total) {
		EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Patient> cq = cb.createQuery(Patient.class);
        Root<Patient> patientRoot = cq.from(Patient.class);
        cq.select(patientRoot);
        return em.createQuery(cq)
                .setFirstResult(pageid-1)
                .setMaxResults(total)
                .getResultList();
	}

    @Override
    public List<Patient> sortSurname(int pageid, String order) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(Patient.class);
        criteria.setFirstResult(pageid-1);
        criteria.setMaxResults (5);
        if (order.equals("asc")) {
            criteria.addOrder(Order.asc("surname"));
        }
        else
            criteria.addOrder(Order.desc("surname"));
        return criteria.list();
    }

    @Override
    public List<Event> sortEventsDate(String order, int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(Event.class).add(Restrictions.eq("patient", getById(id)));
        if (order.equals("asc")) {
            criteria.addOrder(Order.asc("dateTimeEvent"));
        }
        else
            criteria.addOrder(Order.desc("dateTimeEvent"));
        return criteria.list();
    }

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addPatient(Patient p) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(p);
	}

	@Override
	public void updatePatient(Patient p) {
		Session session = sessionFactory.getCurrentSession();
		session.update(p);
	}

	@Override
	public List<Patient> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Patient> patientsList = session.createQuery("from Patient").list();
		return patientsList;
	}

	@Override
	public List<ProcedureMedicine> getAllProcedureMedicine(){
		Session session = sessionFactory.getCurrentSession();
		List<ProcedureMedicine> procedureMedicineList= session.createQuery("from ProcedureMedicine").list();
		return procedureMedicineList;
	}

	@Override
	public List<Staff> getAllDoctors(){
		Session session = sessionFactory.getCurrentSession();
		List<Staff> staffList= session.createQuery("from Staff").list();
		return staffList;
	}

	@Override
	public Patient getById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Patient p = (Patient) session.load(Patient.class, id);
		return p;
	}

	@Override
	public ProcedureMedicine getProcedureMedicineByTitle(String title) {
		EntityManager em = getEntityManager();
		CriteriaQuery<ProcedureMedicine> cq = em.getCriteriaBuilder().createQuery(ProcedureMedicine.class);
		Root<ProcedureMedicine> from = cq.from(ProcedureMedicine.class);

		cq.select(from);
		cq.where(em.getCriteriaBuilder().equal(from.get("title"),title));

		return em.createQuery(cq).getSingleResult();
	}

	@Override
	public Staff getDoctorBySurname(String surname) {
		EntityManager em = getEntityManager();
		CriteriaQuery<Staff> cq = em.getCriteriaBuilder().createQuery(Staff.class);
		Root<Staff> from = cq.from(Staff.class);

		cq.select(from);
		cq.where(em.getCriteriaBuilder().equal(from.get("surname"),surname));

		Staff staff=em.createQuery(cq).getSingleResult();
		return staff;
	}

	@Override
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Patient p = (Patient) session.load(Patient.class, id);
		if(null != p){
			session.delete(p);
		}
	}

}
