package com.rupp.tola.dev.scoring_management_system.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.rupp.tola.dev.scoring_management_system.dto.request.StudentRequest;
import com.rupp.tola.dev.scoring_management_system.dto.response.StudentResponse;
import org.springframework.data.domain.Page;


public interface StudentService {

	StudentResponse create(StudentRequest request);

	StudentResponse findById(UUID uuid);

	Optional<StudentResponse> findByClassesId(UUID id);

	List<StudentResponse> getAll();

	List<StudentResponse> findByStatus(boolean status);

	StudentResponse update(UUID uuid, StudentRequest request);

	void delete(UUID uuid);

	Page<StudentResponse> getByStatusPagination(Map<String, String> param, Boolean status);

}
