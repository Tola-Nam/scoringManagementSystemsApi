package com.rupp.tola.dev.scoring_management_system.service;

import java.util.List;
import java.util.UUID;

import com.rupp.tola.dev.scoring_management_system.dto.request.RoleRequest;
import com.rupp.tola.dev.scoring_management_system.dto.response.RoleResponse;
import com.rupp.tola.dev.scoring_management_system.entity.Roles;
import com.rupp.tola.dev.scoring_management_system.enums.RoleStatus;

public interface RoleService {

	/**
	 * create role with RoleRequest DTO
	 * @param request
	 * @return RoleResponse DTO
	 */
	RoleResponse create(RoleRequest request);

	/**
	 * Update role by UUID and RoleRequest DTO
	 * @param uuid
	 * @param request
	 * @return RoleResponse DTO
	 */
	RoleResponse update(UUID uuid ,RoleRequest request);

	/**
	 * Delete role with UUID
	 * @param uuid
	 */
	void delete(UUID uuid);

	/**
	 * Retrieve all role with status ACTIVE
	 * @return List of RoleResponse DTO
	 */
	List<RoleResponse> findAll();

	/**
	 * Retrieve Role by UUID
	 * @param uuid
	 * @return RoleResponse DTO
	 */
	RoleResponse findById(UUID uuid);

	/**
	 * Retrieve all roles with status
	 * @param status
	 * @return List of RoleResponse DTO
	 */
	List<RoleResponse> findByStatus(RoleStatus status);

}
