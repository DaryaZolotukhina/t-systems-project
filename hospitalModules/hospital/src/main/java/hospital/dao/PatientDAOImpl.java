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
	public List<Procedure> getAllProcedure(){
		Session session = sessionFactory.getCurrentSession();
		List<Procedure> procedureList= session.createQuery("from Procedure").list();
		return procedureList;
	}

	@Override
	public Patient getById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Patient p = (Patient) session.load(Patient.class, id);
		return p;
	}

	@Override
	public Procedure getProcedureByTitle(String title) {
		EntityManager em = getEntityManager();
		CriteriaQuery<Procedure> cq = em.getCriteriaBuilder().createQuery(Procedure.class);
		Root<Procedure> from = cq.from(Procedure.class);

		cq.select(from);
		cq.where(em.getCriteriaBuilder().equal(from.get("title"),title));

		return em.createQuery(cq).getSingleResult();
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
