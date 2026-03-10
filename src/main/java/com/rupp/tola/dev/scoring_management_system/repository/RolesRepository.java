package com.rupp.tola.dev.scoring_management_system.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rupp.tola.dev.scoring_management_system.entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, UUID> {
	boolean existsByName(String name);

	List<Roles> findByStatus(String status);

	Optional<Roles> findByNameAndStatus(String name, String name1);

	boolean existsByNameAndIdNot(String roleName, UUID uuid);
}