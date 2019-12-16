package hospital.component;

import hospital.dao.DiagnosisDAO;
import hospital.model.Diagnosis;
import hospital.service.serviceImpl.DiagnosisServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.Assert.*;

public class CalculationDateUtilsTest {

    private CalculationDateUtils sut;
    private DateComponent dateComponent;

    @Before
    public void setUp(){
        dateComponent= Mockito.mock(DateComponent.class);
        sut=new CalculationDateUtils(dateComponent);
    }

    public static Calendar prepareDate(){
        Calendar date=Calendar.getInstance();
        date.set(2019, 0, 1,0,0,0);
        return date;
    }


    @Test
    public void calcDate() {
        Calendar date=prepareDate();

        Calendar calendar=Calendar.getInstance();
        calendar.set(2019,0,7,0,0,0);
        Date date1 = calendar.getTime();

        calendar.set(2019,0,8,0,0,0);
        Date date2 = calendar.getTime();

        List<Date> dates=new ArrayList<>();
        dates.add(date1);
        dates.add(date2);

        Mockito.when(dateComponent.getCurrentInstance()).thenReturn(date);
        List<Date> result = sut.calcDate(1,3);

        // then
        Assert.assertEquals(dates, result);
    }

    @Test
    public void calcDateTime() {
        Calendar date=prepareDate();

        Mockito.when(dateComponent.getCurrentInstance()).thenReturn(date);
        List<Date> result = sut.calcDateTime(2,27);

        // then
        Assert.assertEquals(1, result);
    }
}