package hospital.component;

import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class DateComponent {
    public Calendar getCurrentInstance(){
        return Calendar.getInstance();
    }
}
