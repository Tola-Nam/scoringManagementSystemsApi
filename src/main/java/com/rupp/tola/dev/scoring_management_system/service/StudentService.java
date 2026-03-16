package com.rupp.tola.dev.scoring_management_system.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.rupp.tola.dev.scoring_management_system.dto.request.StudentRequest;
import com.rupp.tola.dev.scoring_management_system.dto.response.StudentResponse;
import org.springframework.data.domain.Page;

import com.rupp.tola.dev.scoring_management_system.entity.Students;



public interface StudentService {

	StudentResponse createStudents(StudentRequest request);

	StudentResponse getById(UUID id);

	Optional<StudentResponse> findByClassesId(UUID id);

	List<StudentResponse> getStudents();

	Page<Students> getByStatusPagination(Map<String, String> param, Boolean status);

}
