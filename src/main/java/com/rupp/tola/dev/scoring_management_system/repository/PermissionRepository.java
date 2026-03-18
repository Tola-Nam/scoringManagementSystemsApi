package com.rupp.tola.dev.scoring_management_system.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rupp.tola.dev.scoring_management_system.entity.Permissions;

@Repository
public interface PermissionRepository extends JpaRepository<Permissions, UUID>{
    Optional<Permissions> findByName(String name);
    boolean existsByNameAndModule(String name , String module);
    List<Permissions> findByModule(String module);
    Set<Permissions> findByIdIn(List<UUID> id);
}
