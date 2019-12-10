package hospital.dao;

import hospital.model.Procedure;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static hospital.dao.PatientDAOImpl.getEntityManager;

@Repository
public class ProcedureDAOImpl implements ProcedureDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public List<Procedure> getAllProcedure(){
        Session session = sessionFactory.getCurrentSession();
        List<Procedure> procedureList= session.createQuery("from Procedure").list();
        return procedureList;
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
}
