package hospital.dao;

import hospital.model.Staff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static hospital.dao.PatientDAOImpl.getEntityManager;

@Repository
public class DoctorDAOImpl implements DoctorDAO{

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public List<Staff> getAllDoctors(){
        Session session = sessionFactory.getCurrentSession();
        List<Staff> staffList= session.createQuery("from Staff").list();
        return staffList;
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
}
