package com.rupp.tola.dev.scoring_management_system.mapper;

import com.rupp.tola.dev.scoring_management_system.dto.request.PermissionRequest;
import com.rupp.tola.dev.scoring_management_system.dto.response.PermissionResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import com.rupp.tola.dev.scoring_management_system.dto.PermissionDTO;
import com.rupp.tola.dev.scoring_management_system.entity.Permissions;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "createdAt" , ignore = true)
    @Mapping(target = "roles" , ignore = true)
    Permissions toEntity(PermissionRequest request);

    @Mapping(target = "roleIds" , ignore = true)
    PermissionResponse toResponse(Permissions permissions);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "createdAt" , ignore = true)
    @Mapping(target = "roles" , ignore = true)
    void updateFromRequest(@MappingTarget Permissions permissions, PermissionRequest request);
}
