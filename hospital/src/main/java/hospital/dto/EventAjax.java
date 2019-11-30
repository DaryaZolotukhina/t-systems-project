package hospital.dto;

import hospital.model.Medicine;
import hospital.model.Procedure;
import hospital.model.StatusEvent;

import java.util.Date;

public class EventAjax {
        private Integer id;
        private Date dateTimeEvent;
        private String medicine;
        private String procedure;
        private StatusEvent statusEvent;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
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
