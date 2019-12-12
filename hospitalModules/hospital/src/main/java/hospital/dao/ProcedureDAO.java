package hospital.dao;

import hospital.model.Procedure;

import java.util.List;

public interface ProcedureDAO {

    List<Procedure> getAllProcedure();

    Procedure getProcedureByTitle(String title);
}
