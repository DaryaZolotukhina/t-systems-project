package hospital.service.serviceImpl;

import hospital.dao.EventDAO;
import hospital.dao.PrescriptionDAO;
import hospital.dto.StaffDto;
import hospital.dto.event.EventUIDto;
import hospital.dto.prescription.PrescriptionDto;
import hospital.model.Event;
import hospital.model.Prescription;
import hospital.model.Staff;
import hospital.model.StatusEvent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.*;

public class EventServiceImplTest {

    private EventServiceImpl sut;
    private EventDAO eventDAO;
    private Event event;
    private StatusEvent statusEvent;

    @Before
    public void setUp(){
        eventDAO= Mockito.mock(EventDAO.class);
        sut=new EventServiceImpl(eventDAO);

    }

    @Test
    public void testGetById_1_returnEntity1() {
        event=new Event();
        event.setId(BigInteger.valueOf(1));

        // when
        Mockito.when(eventDAO.getEventById(BigInteger.valueOf(1))).thenReturn(event);
        Event result = sut.getById(BigInteger.valueOf(1));

        // then
        Assert.assertEquals(event, result);
    }

    @Test
    public void testGetStatusEventByTitle_completed_returnEntityCompleted() {
        statusEvent=new StatusEvent();
        statusEvent.setId(BigInteger.valueOf(1));
        statusEvent.setTitle("completed");

        // when
        Mockito.when(eventDAO.getStatusEventByTitle("completed")).thenReturn(statusEvent);
        StatusEvent result = sut.getStatusEventByTitle("completed");

        // then
        Assert.assertEquals(statusEvent, result);
    }

    @Test
    public void testGetAll() {
        List<EventUIDto> eventUIDtoList=sut.getAllEvents(BigInteger.valueOf(1));

        Mockito.verify(eventDAO, Mockito.times(1)).getAllEvents(BigInteger.valueOf(1));
    }
}