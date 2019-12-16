package hospital.component;

import org.springframework.stereotype.Component;

import org.jetbrains.annotations.NotNull;
import java.time.DayOfWeek;
import java.util.List;

@Component
public class CalculatingBitMasks {

    enum Hours {
        h0("00:00"),
        h1("01:00"),
        h2("02:00"),
        h3("03:00"),
        h4("04:00"),
        h5("05:00"),
        h6("06:00"),
        h7("07:00"),
        h8("08:00"),
        h9("09:00"),
        h10("10:00"),
        h11("11:00"),
        h12("12:00"),
        h13("13:00"),
        h14("14:00"),
        h15("15:00"),
        h16("16:00"),
        h17("17:00"),
        h18("18:00"),
        h19("19:00"),
        h20("20:00"),
        h21("21:00"),
        h22("22:00"),
        h23("23:00");
        private final String text;

        Hours(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    @NotNull
    public Integer weekToBitMask(@NotNull List<String> weeks) {
        DayOfWeek[] dayOfWeeks = DayOfWeek.values();
        int result=0;
        for (String str : weeks) {
            for (int i = 0; i < dayOfWeeks.length; i++) {
                if (str.toUpperCase().equals(dayOfWeeks[i].toString())){
                    result += (int) Math.pow(2, i);
                }
            }
        }
        return result;
    }

    @NotNull
    public Integer dayToBitMask(@NotNull List<String> days) {
        Hours[] hours = Hours.values();
        int result=0;
        for (String str : days) {
            for (int i = 0; i < hours.length; i++) {
                if (str.equals(hours[i].toString())){
                    result += (int) Math.pow(2, i);
                }
            }
        }
        return result;
    }
}
