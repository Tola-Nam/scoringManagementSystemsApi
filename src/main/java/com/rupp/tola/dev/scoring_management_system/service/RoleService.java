package com.rupp.tola.dev.scoring_management_system.service;

import java.util.List;
import java.util.UUID;

import com.rupp.tola.dev.scoring_management_system.entity.Roles;

public interface RoleService {
	Roles createRoles(Roles roles);

	Roles getById(UUID id);

	List<Roles> roles();

	List<Roles> findByActive(Boolean status);

	Roles editRole(UUID id, Roles rolesUpdateRoles);

	Roles updateStatus(UUID id, Boolean active);

}
