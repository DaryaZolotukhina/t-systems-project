package hospital.dao;

import hospital.model.Event;
import hospital.model.Patient;
import hospital.model.StatusEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventDAOImpl implements EventDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public StatusEvent getStatusEventById(int id){
        Session session = this.sessionFactory.getCurrentSession();
        StatusEvent statusEvent = (StatusEvent) session.load(StatusEvent.class, new Integer(id));
        return statusEvent;
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
