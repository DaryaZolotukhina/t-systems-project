package hospital.dto.mappers;

import hospital.dto.StaffDto;
import hospital.model.Staff;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StaffMapper {
    StaffMapper STAFF_MAPPER = Mappers.getMapper(StaffMapper.class);
    StaffDto fromStaff(Staff staff);
    @InheritInverseConfiguration
    Staff toStaff(StaffDto staffDto);
}
