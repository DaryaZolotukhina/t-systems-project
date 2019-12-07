package hospital.dto;

import hospital.model.Prescription;

import java.util.List;

public class ErrorMessage {

    private String errMsg;
    private List<PrescriptionError> prescriptionList;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<PrescriptionError> getPrescriptionList() {
        return prescriptionList;
    }

    public void setPrescriptionList(List<PrescriptionError> prescriptionList) {
        this.prescriptionList = prescriptionList;
    }
}
