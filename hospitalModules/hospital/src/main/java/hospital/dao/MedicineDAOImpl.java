package hospital.dao;

import hospital.model.Medicine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static hospital.dao.PatientDAOImpl.getEntityManager;

@Repository
public class MedicineDAOImpl implements MedicineDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public List<Medicine> getAllMedicine(){
        Session session = sessionFactory.getCurrentSession();
        List<Medicine> medicineList= session.createQuery("from Medicine").list();
        return medicineList;
    }

    @Override
    public Medicine getMedicineByTitle(String title) {
        EntityManager em = getEntityManager();
        CriteriaQuery<Medicine> cq = em.getCriteriaBuilder().createQuery(Medicine.class);
        Root<Medicine> from = cq.from(Medicine.class);

        cq.select(from);
        cq.where(em.getCriteriaBuilder().equal(from.get("title"),title));

        return em.createQuery(cq).getSingleResult();
    }
}
