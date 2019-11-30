package hospital.dao;

import hospital.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static hospital.dao.PatientDAOImpl.getEntityManager;

@Repository
public class EventDAOImpl implements EventDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public void updateEvent(Event event) {
        Session session = sessionFactory.getCurrentSession();
        session.update(event);
    }

    @Override
    public void deleteEvent(Event event) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(event);
    }

    @Override
    public StatusEvent getStatusEventById(int id){
        Session session = sessionFactory.getCurrentSession();
        StatusEvent statusEvent = (StatusEvent) session.load(StatusEvent.class, id);
        return statusEvent;
    }

    @Override
    public List<Event> getAllEvents(int id){
        Session session = sessionFactory.getCurrentSession();
        Patient p = (Patient) session.load(Patient.class, id);
        List<Event> events=p.getEvents();
        return events;
    }

    @Override
    public List<Event> getAllEvents(){
        Session session = sessionFactory.getCurrentSession();
        List<Event> eventList= session.createQuery("from Event").list();
        return eventList;
    }


    @Override
    public void saveEvent(Event event) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(event);
    }

    @Override
    public Event getEventById(int id){
        Session session = sessionFactory.getCurrentSession();
        Event event = (Event) session.load(Event.class, id);
        return event;
    }

    @Override
    public StatusEvent getStatusEventByTitle(String title) {
        EntityManager em = getEntityManager();
        CriteriaQuery<StatusEvent> cq = em.getCriteriaBuilder().createQuery(StatusEvent.class);
        Root<StatusEvent> from = cq.from(StatusEvent.class);

        cq.select(from);
        cq.where(em.getCriteriaBuilder().equal(from.get("title"),title));

        return em.createQuery(cq).getSingleResult();
    }

    @Override
    public void update(Event event) {
       sessionFactory.getCurrentSession().merge(event);
        //session.update(event);
    }
}
