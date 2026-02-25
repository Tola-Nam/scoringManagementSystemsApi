package com.rupp.tola.dev.scoring_management_system.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.rupp.tola.dev.scoring_management_system.DTO.ClassDTO;
import com.rupp.tola.dev.scoring_management_system.entity.Classes;

@Mapper(componentModel = "spring")
public interface ClassesMapper {
	ClassesMapper INSTANCE = Mappers.getMapper(ClassesMapper.class);

	@Mapping(target = "id", ignore = true)
	Classes toClass(ClassDTO classDTO);

	ClassDTO toClassesDto(Classes classes);
}
