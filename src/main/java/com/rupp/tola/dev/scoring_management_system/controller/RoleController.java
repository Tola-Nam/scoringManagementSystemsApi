package com.rupp.tola.dev.scoring_management_system.controller;

import com.rupp.tola.dev.scoring_management_system.data.SingleResponse;
import com.rupp.tola.dev.scoring_management_system.dto.request.RoleRequest;
import com.rupp.tola.dev.scoring_management_system.dto.response.RoleResponse;
import com.rupp.tola.dev.scoring_management_system.enums.RoleStatus;
import com.rupp.tola.dev.scoring_management_system.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @Operation(summary = "Retrieve role by UUID.")
    @GetMapping("/{uuid}")
    public ResponseEntity<SingleResponse<RoleResponse>> findById(@PathVariable UUID uuid) {
        RoleResponse roleResponse = roleService.findById(uuid);
        return ResponseEntity.ok().body(SingleResponse.success("Success to retrieve role.", roleResponse));
    }

    @Operation(summary = "Retrieve all roles.")
    @GetMapping
    public ResponseEntity<SingleResponse<List<RoleResponse>>> findAll() {
        List<RoleResponse> response = roleService.findAll();
        return ResponseEntity.ok().body(SingleResponse.success("Success to retrieve all roles.", response));
    }

    @Operation(summary = "Retrieve all roles with RequestParam RoleStatus(ACTIVE , INACTIVE , DELETE)")
    @GetMapping("/status")
    public ResponseEntity<SingleResponse<List<RoleResponse>>> findByStatus(@RequestParam(defaultValue = "ACTIVE") RoleStatus status) {
        List<RoleResponse> responses = roleService.findByStatus(status);
        return ResponseEntity.ok().body(SingleResponse.success("Success to retrieve all roles.", responses));
    }

    @Operation(summary = "Create role with RoleRequest.")
    @PostMapping
    public ResponseEntity<SingleResponse<RoleResponse>> create(@RequestBody RoleRequest request) {
        RoleResponse response = roleService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(SingleResponse.success("Successfully created role.", response));
    }

    @Operation(summary = "Update role with UUID pathVariable and RoleRequest.")
    @PutMapping("/{uuid}")
    public ResponseEntity<SingleResponse<RoleResponse>> update(@PathVariable UUID uuid, @RequestBody RoleRequest request) {
        RoleResponse response = roleService.update(uuid , request);
        return ResponseEntity.ok().body(SingleResponse.success("Successfully updated role.", response));
    }

    @Operation(summary = "Delete role with UUID pathVariable.")
    @DeleteMapping("/{uuid}")
    public ResponseEntity<SingleResponse<Void>> delete(@PathVariable UUID uuid) {
        roleService.delete(uuid);
        return ResponseEntity.ok().body(SingleResponse.success("Successfully deleted role." , null));
    }

}
