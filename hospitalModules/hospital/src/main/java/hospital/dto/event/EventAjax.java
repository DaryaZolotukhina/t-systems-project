package hospital.dto.event;

import hospital.model.Medicine;
import hospital.model.Patient;
import hospital.model.Procedure;
import hospital.model.StatusEvent;

import java.math.BigInteger;
import java.util.Date;

public class EventAjax {
        private BigInteger id;
        private Date dateTimeEvent;
        private String medicine;
        private String procedure;
        private StatusEvent statusEvent;
        private int idPatient;
        private String surnamePatient;
        private int idStaff;

    public int getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
    }

    public int getIdPatient() {
            return idPatient;
        }

        public void setIdPatient(int idPatient) {
            this.idPatient = idPatient;
        }

        public String getSurnamePatient() {
            return surnamePatient;
        }

        public void setSurnamePatient(String surnamePatient) {
            this.surnamePatient = surnamePatient;
        }

        public BigInteger getId() {
                    return id;
                }

        public void setId(BigInteger id) {
            this.id = id;
        }

        public Date getDateTimeEvent() {
            return dateTimeEvent;
        }

        public void setDateTimeEvent(Date dateTimeEvent) {
            this.dateTimeEvent = dateTimeEvent;
        }

        public String getMedicine() {
            return medicine;
        }

        public void setMedicine(String medicine) {
            this.medicine = medicine;
        }

        public String getProcedure() {
            return procedure;
        }

        public void setProcedure(String procedure) {
            this.procedure = procedure;
        }

        public StatusEvent getStatusEvent() {
            return statusEvent;
        }

        public void setStatusEvent(StatusEvent statusEvent) {
            this.statusEvent = statusEvent;
        }

}
