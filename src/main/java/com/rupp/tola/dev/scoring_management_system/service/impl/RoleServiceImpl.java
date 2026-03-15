package com.rupp.tola.dev.scoring_management_system.service.impl;

import com.rupp.tola.dev.scoring_management_system.dto.request.AssignPermissionRequest;
import com.rupp.tola.dev.scoring_management_system.dto.request.RoleRequest;
import com.rupp.tola.dev.scoring_management_system.dto.response.RoleResponse;
import com.rupp.tola.dev.scoring_management_system.entity.Permissions;
import com.rupp.tola.dev.scoring_management_system.entity.Roles;
import com.rupp.tola.dev.scoring_management_system.entity.Users;
import com.rupp.tola.dev.scoring_management_system.exception.DuplicateResourceException;
import com.rupp.tola.dev.scoring_management_system.exception.ResourceNotFoundException;
import com.rupp.tola.dev.scoring_management_system.mapper.RoleMapper;
import com.rupp.tola.dev.scoring_management_system.repository.PermissionRepository;
import com.rupp.tola.dev.scoring_management_system.repository.RolesRepository;
import com.rupp.tola.dev.scoring_management_system.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rupp.tola.dev.scoring_management_system.service.RoleService;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final RolesRepository rolesRepository;
	private final RoleMapper roleMapper;
	private final AuthService authService;
	private final PermissionRepository permissionRepository;

	@Override
	public RoleResponse create(RoleRequest request) {

		if (rolesRepository.existsByName(request.getName())) {
			log.info("Role already exists with the name {}", request.getName());
			throw new DuplicateResourceException("Role name already exists");
		}

		Roles roles = roleMapper.toEntity(request);
		String roleName = request.getName().toUpperCase();
		if (!roleName.startsWith("ROLE_")) {
			roleName = "ROLE_" + roleName;
		}

		roles.setName(roleName);
		if (request.getUserIds() != null) {
			Set<Users> users = request.getUserIds().stream()
					.map(userId -> {
						Users user = authService.getUser(userId);
						if(user != null) {
							user.setRoles(roles);
						}
						return user;
					})
					.collect(Collectors.toSet());
			roles.setUsers(users);
		}
		Roles saved = rolesRepository.save(roles);
		log.info("Role created with id {}", saved.getId());
		return toResponse(saved);
	}

	@Override
	public RoleResponse update(UUID uuid, RoleRequest request) {
		Roles roles = findByIdOrThrow(uuid);

		if (request.getName() != null) {
			String roleName = request.getName().toUpperCase();
			if (!roleName.startsWith("ROLE_")) {
				roleName = "ROLE_" + roleName;
			}
			if (rolesRepository.existsByNameAndIdNot(roleName, uuid)) {
				log.info("Role already exists with the name {}", roleName);
				throw new IllegalArgumentException("Role name already exists");
			}
			request.setName(roleName);
		}

		roleMapper.updateFromRequest(roles, request);

		if(request.getUserIds() != null && !request.getUserIds().isEmpty()) {
			Set<Users> users = request.getUserIds()
					.stream()
					.map(authService::getUser)
					.collect(Collectors.toSet());
			roles.setUsers(users);
		}else {
			roles.setUsers(new HashSet<>());
		}

		Roles saved = rolesRepository.save(roles);
		log.info("Role updated with id {}", saved.getId());
		return toResponse(roles);
	}

	@Override
	public List<RoleResponse> findAll() {
		List<Roles> roles = rolesRepository.findAll();
		log.info("Roles found with all {}", roles);
		return roles.stream()
				.map(this::toResponse)
				.toList();
	}

	@Override
	public RoleResponse findById(UUID uuid) {
		Roles roles = findByIdOrThrow(uuid);
		log.info("Role found with id {}", roles.getId());
		return toResponse(roles);
	}

	@Override
	public void updateStatus(UUID uuid, String status) {
		Roles roles = findByIdOrThrow(uuid);
		roles.setStatus(status);
		rolesRepository.save(roles);
	}

	@Override
	public List<RoleResponse> findByActive(String status) {
		List<Roles> roles = rolesRepository.findByStatus(status);
		log.info("Roles found with status {}", roles);
		return roles.stream()
				.map(this::toResponse)
				.toList();
	}

	@Override
	public RoleResponse addPermission(UUID roleId, AssignPermissionRequest request) {

		Roles roles = findByIdOrThrow(roleId);
		Set<Permissions> permissions = permissionRepository.findByIdIn(request.getPermissionIds());
		roles.getPermissions().addAll(permissions);
		Roles saved = rolesRepository.save(roles);
		log.info("Role added with id {}" , saved.getId());
		return toResponse(saved);
	}

	@Override
	public RoleResponse setPermission(UUID roleId, AssignPermissionRequest request) {
		Roles roles = findByIdOrThrow(roleId);
		Set<Permissions> permissions = permissionRepository.findByIdIn(request.getPermissionIds());
		roles.getPermissions().clear();
		roles.getPermissions().addAll(permissions);
		log.info("Role added with id {}" , roles.getId());
		Roles saved = rolesRepository
				.save(roles);
		return toResponse(saved);
	}

	@Override
	public void deletePermission(UUID roleId, UUID permissionId) {
		Roles roles = findByIdOrThrow(roleId);
		roles.getPermissions().removeIf(permission -> permission.getId().equals(permissionId));
		rolesRepository.save(roles);
	}

	private Roles findByIdOrThrow(UUID roleId) {
		return rolesRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found with ID: " + roleId));
	}

	private RoleResponse toResponse(Roles role) {
		RoleResponse response = roleMapper.toResponse(role);
		if(role.getUsers() != null && !role.getUsers().isEmpty()) {
			List<UUID> uuids = role.getUsers().stream()
					.map(Users::getId)
					.toList();
			response.setUserIds(uuids);
		}
		return response;
	}
}
