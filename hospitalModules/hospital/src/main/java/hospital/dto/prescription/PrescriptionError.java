package hospital.dto.prescription;

import java.math.BigInteger;

public class PrescriptionError {
    private BigInteger id;
    private Integer period;
    private Float dose;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Float getDose() {
        return dose;
    }

    public void setDose(Float dose) {
        this.dose = dose;
    }

}
