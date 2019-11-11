package hospital.exception;

import hospital.model.Prescription;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DischargeException {

    private String errCode;
    private String errMsg;
    private List<Prescription> prescriptionList;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

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
    }


    public DischargeException error(List<Prescription> prescriptionList){
        this.prescriptionList=prescriptionList;
        this.errCode="200";
        this.errMsg="Not all prescriptions were completed:";
        return this;
    }
}
