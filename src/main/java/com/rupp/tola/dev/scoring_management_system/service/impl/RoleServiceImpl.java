package com.rupp.tola.dev.scoring_management_system.service.impl;

import com.rupp.tola.dev.scoring_management_system.dto.request.RoleRequest;
import com.rupp.tola.dev.scoring_management_system.dto.response.RoleResponse;
import com.rupp.tola.dev.scoring_management_system.entity.Roles;
import com.rupp.tola.dev.scoring_management_system.entity.Users;
import com.rupp.tola.dev.scoring_management_system.enums.RoleStatus;
import com.rupp.tola.dev.scoring_management_system.mapper.RoleMapper;
import com.rupp.tola.dev.scoring_management_system.repository.RolesRepository;
import com.rupp.tola.dev.scoring_management_system.security.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.rupp.tola.dev.scoring_management_system.service.RoleService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final RolesRepository rolesRepository;
	private final RoleMapper roleMapper;
	private final AuthService authService;

	@Override
	public RoleResponse create(RoleRequest request) {
		String roleName = request.getName().toUpperCase();
		if (!roleName.startsWith("ROLE_")) {
			roleName = "ROLE_" + roleName;
		}

		if (rolesRepository.existsByName(roleName)) {
			log.info("Role already exists with the name {}", roleName);
			throw new IllegalArgumentException("Role name already exists");
		}
		Roles roles = roleMapper.toEntity(request);
		roles.setName(roleName);
		Users users = authService.getUser(request.getUserId());
		roles.setUsers(List.of(users));
		Roles saved = rolesRepository.save(roles);
		log.info("Role created with id {}", saved.getId());
		return roleMapper.toResponse(saved);
	}

	@Override
	public RoleResponse update(UUID uuid, RoleRequest request) {
		Roles roles = rolesRepository.findById(uuid)
				.orElseThrow(() -> new NoSuchElementException("Role not found with ID: " + uuid));

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
		Roles saved = rolesRepository.save(roles);
		log.info("Role updated with id {}", saved.getId());
		return roleMapper.toResponse(saved);
	}

	@Override
	public void delete(UUID uuid) {
		Roles roles = rolesRepository.findById(uuid)
				.orElseThrow(() -> new NoSuchElementException("Role not found with ID: " + uuid));
		log.info("Role deleted with id {}", roles.getId());
		rolesRepository.delete(roles);
	}

	@Override
	public List<RoleResponse> findAll() {
		List<Roles> roles = rolesRepository.findAll();
		log.info("Roles found with all {}", roles);
		return roleMapper.toList(roles);
	}

	@Override
	public RoleResponse findById(UUID uuid) {
		Roles roles = rolesRepository.findById(uuid)
				.orElseThrow(() -> new NoSuchElementException("Role not found with ID: " + uuid));
		log.info("Role found with id {}", roles.getId());
		return roleMapper.toResponse(roles);
	}

	@Override
	public List<RoleResponse> findByStatus(RoleStatus status) {
		List<Roles> roles = rolesRepository.findByStatus(RoleStatus.ACTIVE);
		return roleMapper.toList(roles);
	}
}
