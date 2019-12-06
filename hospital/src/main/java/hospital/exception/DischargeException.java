package hospital.exception;

import hospital.model.Prescription;
import org.springframework.stereotype.Component;

import java.util.List;

public class DischargeException extends RuntimeException {

   /* public DischargeException (List<Prescription> prescriptionList){
        this.prescriptionList=prescriptionList;
        this.errMsg="Not all prescriptions were completed:";
    }

    private String errMsg;
    private List<Prescription> prescriptionList;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<Prescription> getPrescriptionList() {
        return prescriptionList;
    }

    public void setPrescriptionList(List<Prescription> prescriptionList) {
        this.prescriptionList = prescriptionList;
    }*/

    public DischargeException() {
        super("Not all prescriptions were completed:");
    }

}
