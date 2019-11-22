package hospital.dto.mappers;

import hospital.dto.PatientDto;
import hospital.dto.ProcedureMedicineTitleDto;
import hospital.model.Patient;
import hospital.model.ProcedureMedicine;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProcedureMedicineMapper {
    ProcedureMedicineMapper PROCEDURE_MEDICINE_MAPPER = Mappers.getMapper(ProcedureMedicineMapper.class);
    ProcedureMedicineTitleDto fromProcedureMedicine(ProcedureMedicine procedureMedicine);
    @InheritInverseConfiguration
    ProcedureMedicine toProcedureMedicine(ProcedureMedicineTitleDto procedureMedicine);
}
