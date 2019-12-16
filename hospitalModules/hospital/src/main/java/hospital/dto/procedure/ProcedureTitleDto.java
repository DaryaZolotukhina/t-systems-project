package hospital.dto.procedure;

import java.math.BigInteger;

public class ProcedureTitleDto {
    private BigInteger id;
    private String title;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
