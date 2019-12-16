package hospital.dto.prescription;

import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.List;

public class CreatePrescriptionRequest {
    private String period;
    private List<String> daySchedule;
    private List<String> weekSchedule;
    @NotBlank(message = "Diagnosis type is mandatory")
    @Pattern(regexp = "^[A-z]+$")
    @Size(min = 3)
    private String diagnosisType;
    @Size(min = 3)
    @NotBlank(message = "Diagnosis is mandatory")
    @Pattern(regexp = "^[A-z]+$")
    private String diagnosis;
    private String procedure;
    private String medicine;
    private BigInteger staffId;

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public List<String> getDaySchedule() {
        return daySchedule;
    }

    public void setDaySchedule(List<String> daySchedule) {
        this.daySchedule = daySchedule;
    }

    public List<String> getWeekSchedule() {
        return weekSchedule;
    }

    public void setWeekSchedule(List<String> weekSchedule) {
        this.weekSchedule = weekSchedule;
    }

    public String getDiagnosisType() {
        return diagnosisType;
    }

    public void setDiagnosisType(String diagnosisType) {
        this.diagnosisType = diagnosisType;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public BigInteger getStaffId() {
        return staffId;
    }

    public void setStaffId(BigInteger staffId) {
        this.staffId = staffId;
    }
}
