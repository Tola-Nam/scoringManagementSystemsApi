package com.rupp.tola.dev.scoring_management_system.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.rupp.tola.dev.scoring_management_system.enums.RoleName;
import com.rupp.tola.dev.scoring_management_system.enums.RoleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rupp.tola.dev.scoring_management_system.entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, UUID> {

	boolean existsByName(String name);

	boolean existsByNameAndIdNot(String name, UUID id);

	List<Roles> findByStatus(RoleStatus status);

	Optional<Roles> findByNameAndStatus(String name, RoleStatus status);
}
