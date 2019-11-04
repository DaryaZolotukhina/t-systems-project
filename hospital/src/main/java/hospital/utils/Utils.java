package hospital.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Utils {
    public static Date getNearestDayOfWeek(int dayOfWeek, Calendar now){
        int weekday = now.get(Calendar.DAY_OF_WEEK);

        int days = dayOfWeek - weekday;
        if (days < 0) days += 7;
        now.add(Calendar.DAY_OF_YEAR, days);

        Date date = now.getTime();
        return date;
    }

    public static List<Date> calcDate(int period,String week){
        List<Date> resultDate=new LinkedList<>();
        Calendar now = Calendar.getInstance();
        for(int i=0;i<period;i++){
            int start=0;
            int dayOfWeek=week.indexOf("1", start);
            while(dayOfWeek!=-1){
                int dayOfWeekCalc=dayOfWeek+2;
                Date date=getNearestDayOfWeek(dayOfWeekCalc,now);
                resultDate.add(date);
                now.setTime(date);
                now.add(Calendar.DATE, 1);
                start=dayOfWeek+1;
                dayOfWeek=week.indexOf("1", start);
            }
        }
        return resultDate;
    }

    public static List<Date> calcDateTime(int period,String week){
        List<Date> resultDate=new LinkedList<>();
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, 1);
        now.set(Calendar.HOUR_OF_DAY,0);
        now.set(Calendar.MINUTE,00);
        now.set(Calendar.SECOND,00);
        for(int i=0;i<period;i++){
            int start=0;
            int dayOfWeek=week.indexOf("1", start);
            while(dayOfWeek!=-1){
                switch(dayOfWeek){
                    case 0:
                        now.add(Calendar.HOUR_OF_DAY,9);
                        break;
                    case 1:
                        now.add(Calendar.HOUR_OF_DAY,12);
                        break;
                    case 2:
                        now.add(Calendar.HOUR_OF_DAY,18);
                        break;
                }
                Date date = now.getTime();
                resultDate.add(date);
                start=dayOfWeek+1;
                dayOfWeek=week.indexOf("1", start);
                now = Calendar.getInstance();
                now.add(Calendar.DATE, i+1);
                now.set(Calendar.HOUR_OF_DAY,0);
                now.set(Calendar.MINUTE,00);
                now.set(Calendar.SECOND,00);
            }
            now.add(Calendar.DATE, 1);
        }
        return resultDate;
    }

}
