package com.rupp.tola.dev.scoring_management_system.config;

import com.rupp.tola.dev.scoring_management_system.entity.Permissions;
import com.rupp.tola.dev.scoring_management_system.entity.RefreshTokens;
import com.rupp.tola.dev.scoring_management_system.entity.Roles;
import com.rupp.tola.dev.scoring_management_system.entity.Users;
import com.rupp.tola.dev.scoring_management_system.jwt.JwtService;
import com.rupp.tola.dev.scoring_management_system.repository.PermissionRepository;
import com.rupp.tola.dev.scoring_management_system.repository.RolesRepository;
import com.rupp.tola.dev.scoring_management_system.repository.UsersRepository;
import com.rupp.tola.dev.scoring_management_system.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(DataSeeder.class);

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Seeding data...");

        Permissions adminRead = perm("admin:read" , "Can access GET method." , "ADMIN_MANAGEMENT");
        Permissions adminWrite = perm("admin:write" , "Can access POST and PUT method." , "ADMIN_MANAGEMENT");
        Permissions adminDelete = perm("admin:delete" , "Can access DELETE method." , "ADMIN");

        Roles roleAdmin = createRole("ROLE_ADMIN" , "Only admin can use this role." ,
                "ACTIVE" , Set.of(adminRead, adminWrite, adminDelete));

        createRole("ROLE_STAFF" , "Only staff can use this role." , "ACTIVE" , Set.of(adminRead, adminWrite, adminDelete));

        if(!usersRepository.existsByEmail("admin@gmail.com")) {
            Users admin = new Users();
            admin.setFullName("admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setVerified(true);
            admin.setRoles(roleAdmin);
            admin.setVerificationToken(jwtService.generateToken("admin"));
            RefreshTokens refreshTokens = refreshTokenService.create();
            refreshTokens.setUsers(admin);
            admin.setRefreshTokens(refreshTokens);
            usersRepository.save(admin);
        }

    }

    public Permissions perm(String name, String description, String module) {
        return permissionRepository.findByName(name)
                .orElseGet(() -> {
                    Permissions permission = new Permissions();
                    permission.setName(name);
                    permission.setDescription(description);
                    permission.setModule(module);
                    return permissionRepository.save(permission);
                });
    }

    public Roles createRole(String name , String description , String status , Set<Permissions> permissions) {
        return rolesRepository.findByName(name)
                .orElseGet(() -> {
                    Roles roles = new Roles();
                    roles.setName(name);
                    roles.setDescription(description);
                    roles.setStatus(status);
                    roles.setPermissions(permissions);
                    return rolesRepository.save(roles);
                });
    }
}
