package com.rupp.tola.dev.scoring_management_system.mapper;

import com.rupp.tola.dev.scoring_management_system.dto.request.RoleRequest;
import com.rupp.tola.dev.scoring_management_system.dto.response.RoleResponse;
import com.rupp.tola.dev.scoring_management_system.entity.Roles;
import com.rupp.tola.dev.scoring_management_system.entity.Users;
import com.rupp.tola.dev.scoring_management_system.security.AuthService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class RoleMapper {

    @Autowired
    protected AuthService authService;

    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "users" , source = "userId" , qualifiedByName = "mapUserIdToUsers")
    @Mapping(target = "users" , ignore = true)
    public abstract Roles toEntity(RoleRequest request);

    @Mapping(target = "userId", source = "users", qualifiedByName = "mapUsersToUserIds")
    public abstract RoleResponse toResponse(Roles role);

    public abstract List<RoleResponse> toList(List<Roles> roles);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", source = "userId" , qualifiedByName = "mapUserIdToUsers")
    public abstract void updateFromRequest(@MappingTarget Roles roles, RoleRequest request);

    @Named("mapUsersToUserIds")
    protected List<UUID> mapUsersToUserIds(List<Users> users) {
        if (users == null) {
            return null;
        }
        return users.stream().map(Users::getId).collect(Collectors.toList());
    }

    @Named("mapUserIdToUsers")
    protected List<Users> mapUserIdToUsers(UUID userId) {
        if (userId == null) {
            return null;
        }
        return List.of(authService.getUser(userId));
    }
}