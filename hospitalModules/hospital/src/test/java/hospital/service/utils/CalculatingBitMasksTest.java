package hospital.service.utils;

import hospital.component.CalculatingBitMasks;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CalculatingBitMasksTest {

    private CalculatingBitMasks sud;

    @Before
    public void setUp(){
        sud=new CalculatingBitMasks();
    }

    @Test
    public void testWeekToBitMask_Monday_return1() {
        List<String> list= Collections.singletonList("Monday");

        Integer result=sud.weekToBitMask(list);

        assertEquals(Integer.valueOf(1),result);
    }

    @Test
    public void testWeekToBitMask_Tuesday_return2() {
        List<String> list= Collections.singletonList("Tuesday");

        Integer result=sud.weekToBitMask(list);

        assertEquals(Integer.valueOf(2),result);
    }

    @Test
    public void testWeekToBitMask_invalidString_return0() {
        List<String> list= Collections.singletonList("jfjd");

        Integer result=sud.weekToBitMask(list);

        assertEquals(Integer.valueOf(0),result);
    }

    @Test
    public void testWeekToBitMask_emptyCollection_return0() {
        Integer result=sud.weekToBitMask(Collections.emptyList());

        assertEquals(Integer.valueOf(0),result);
    }

    @Test
    public void testDayToBitMask_0000_return1(){
        List<String> list= Collections.singletonList("00:00");

        Integer result=sud.dayToBitMask(list);

        assertEquals(Integer.valueOf(1),result);
    }

    @Test
    public void testDayToBitMask_0000_0100_return3(){
        List<String> list= new ArrayList<>();
        list.add("00:00");
        list.add("01:00");

        Integer result=sud.dayToBitMask(list);

        assertEquals(Integer.valueOf(3),result);
    }

    @Test
    public void testDayToBitMask_invalidString_return0() {
        List<String> list= Collections.singletonList("jfjd");

        Integer result=sud.dayToBitMask(list);

        assertEquals(Integer.valueOf(0),result);
    }

    @Test
    public void testDayToBitMask_emptyCollection_return0() {
        Integer result=sud.dayToBitMask(Collections.emptyList());

        assertEquals(Integer.valueOf(0),result);
    }
}
