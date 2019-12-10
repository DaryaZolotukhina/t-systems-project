package hospital.dao;

import hospital.model.Diagnosis;
import hospital.model.DiagnosisType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static hospital.dao.PatientDAOImpl.getEntityManager;

@Repository
public class DiagnosisDAOImpl implements DiagnosisDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public void addDiagnosis(Diagnosis d) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(d);
    }

    @Override
    public List<DiagnosisType> getAllDiagnosisType(){
        Session session = sessionFactory.getCurrentSession();
        List<DiagnosisType> diagnosisTypeList= session.createQuery("from DiagnosisType").list();
        return diagnosisTypeList;
    }

    @Override
    public DiagnosisType getDiagnosisTypeByTitle(String title) {
        EntityManager em = getEntityManager();
        CriteriaQuery<DiagnosisType> cq = em.getCriteriaBuilder().createQuery(DiagnosisType.class);
        Root<DiagnosisType> from = cq.from(DiagnosisType.class);

        cq.select(from);
        cq.where(em.getCriteriaBuilder().equal(from.get("title"),title));

        return em.createQuery(cq).getSingleResult();
    }

    @Override
    public Diagnosis getDiagnosisByTitle(String title) {
        EntityManager em = getEntityManager();
        CriteriaQuery<Diagnosis> cq = em.getCriteriaBuilder().createQuery(Diagnosis.class);
        Root<Diagnosis> from = cq.from(Diagnosis.class);

        cq.select(from);
        cq.where(em.getCriteriaBuilder().equal(from.get("title"),title));

        return em.createQuery(cq).getSingleResult();
    }
}
