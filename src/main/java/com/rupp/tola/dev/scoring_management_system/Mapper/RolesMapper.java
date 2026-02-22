package com.rupp.tola.dev.scoring_management_system.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.rupp.tola.dev.scoring_management_system.DTO.RolesDTO;
import com.rupp.tola.dev.scoring_management_system.entity.Roles;

@Mapper(componentModel = "spring")
public interface RolesMapper {
	RolesMapper INSTANCE = Mappers.getMapper(RolesMapper.class);

	@Mapping(target = "id", ignore = true)
	Roles toRoles(RolesDTO rolesDTO);

	RolesDTO toRolesDTO(Roles roles);
}
