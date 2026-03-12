package com.rupp.tola.dev.scoring_management_system.service.impl;

import com.rupp.tola.dev.scoring_management_system.dto.request.PermissionRequest;
import com.rupp.tola.dev.scoring_management_system.dto.response.PermissionResponse;
import com.rupp.tola.dev.scoring_management_system.entity.Permissions;
import com.rupp.tola.dev.scoring_management_system.entity.Roles;
import com.rupp.tola.dev.scoring_management_system.exception.DuplicateResourceException;
import com.rupp.tola.dev.scoring_management_system.exception.ResourceNotFoundException;
import com.rupp.tola.dev.scoring_management_system.mapper.PermissionMapper;
import com.rupp.tola.dev.scoring_management_system.repository.PermissionRepository;
import com.rupp.tola.dev.scoring_management_system.repository.RolesRepository;
import com.rupp.tola.dev.scoring_management_system.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;
    private final RolesRepository rolesRepository;

    @Override
    public PermissionResponse create(PermissionRequest request) {
        if (permissionRepository.existsByNameAndModule(request.getName(), request.getModule())) {
            throw new DuplicateResourceException("Permission already exists");
        }
        Permissions permission = permissionMapper.toEntity(request);
        if (request.getRoleIds() != null && !request.getRoleIds().isEmpty()) {
            Set<Roles> roles = rolesRepository.findByIdIn(request.getRoleIds());
            permission.setRoles(roles);
        }
        Permissions saved = permissionRepository.save(permission);
        log.info("Permission created: {}", saved);
        return toResponse(saved);
    }

    @Override
    public PermissionResponse update(UUID id, PermissionRequest request) {
        if (permissionRepository.existsByNameAndModule(request.getName(), request.getModule())) {
            throw new DuplicateResourceException("Permission already exists");
        }
        Permissions permission = findByIdOrThrow(id);
        permissionMapper.updateFromRequest(permission, request);
        if (request.getRoleIds() != null && !request.getRoleIds().isEmpty()) {
            Set<Roles> roles = rolesRepository.findByIdIn(request.getRoleIds());
            permission.setRoles(roles);
        }
        Permissions updated = permissionRepository.save(permission);
        return toResponse(updated);
    }

    @Override
    public void delete(UUID id) {
        Permissions permission = findByIdOrThrow(id);
        permissionRepository.delete(permission);
    }

    @Override
    public List<PermissionResponse> findAll() {
        List<Permissions> permissions = permissionRepository.findAll();
        return permissions.stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public PermissionResponse findById(UUID id) {
        Permissions permission = findByIdOrThrow(id);
        return toResponse(permission);
    }

    @Override
    public List<PermissionResponse> findByModule(String module) {
        List<Permissions> permissions = permissionRepository.findByModule(module);
        return permissions.stream()
                .map(this::toResponse)
                .toList();
    }

    private Permissions findByIdOrThrow(UUID id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found with ID: " + id));
    }

    private PermissionResponse toResponse(Permissions permissions) {
        var response = permissionMapper.toResponse(permissions);
        if (permissions.getRoles() != null && !permissions.getRoles().isEmpty()) {
            List<UUID> roleIds = permissions.getRoles()
                    .stream()
                    .map(Roles::getId)
                    .toList();
            response.setRoleIds(roleIds);
        }
        return response;
    }
}