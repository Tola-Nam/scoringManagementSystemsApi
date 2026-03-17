package com.rupp.tola.dev.scoring_management_system.service.impl;

import java.time.Year;
import java.util.*;

import com.rupp.tola.dev.scoring_management_system.dto.request.StudentRequest;
import com.rupp.tola.dev.scoring_management_system.dto.response.StudentResponse;
import com.rupp.tola.dev.scoring_management_system.exception.ResourceNotFoundException;
import com.rupp.tola.dev.scoring_management_system.mapper.StudentsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.rupp.tola.dev.scoring_management_system.entity.Students;
import com.rupp.tola.dev.scoring_management_system.repository.StudentsRepository;
import com.rupp.tola.dev.scoring_management_system.service.StudentService;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentsRepository studentsRepository;
	private final StudentsMapper studentsMapper;

	@Override
	public StudentResponse create(StudentRequest request) {
		Students students = studentsMapper.toEntity(request);
		Students saved = studentsRepository.save(students);
		return studentsMapper.toResponse(saved);
	}

	@Override
	public StudentResponse findById(UUID uuid) {
		Students students = studentsRepository.findById(uuid)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with id : " + uuid));
		log.info("Student found with id {}", students.getId());
		return studentsMapper.toResponse(students);
	}

	@Override
	public Optional<StudentResponse> findByClassesId(UUID id) {
		return studentsRepository.findByClassesId(id)
				.map(studentsMapper::toResponse);
	}

	@Override
	public List<StudentResponse> getAll() {
		return studentsRepository.findAll().stream()
				.map(studentsMapper::toResponse)
				.toList();
	}

	@Override
	public List<StudentResponse> findByStatus(boolean status) {
		List<Students> students = studentsRepository.findByStatus(status);
		return students.stream()
				.map(studentsMapper::toResponse)
				.toList();
	}

	@Override
	public StudentResponse update(UUID uuid, StudentRequest request) {
		Students students = studentsRepository.findById(uuid)
				.orElseThrow(() -> new NoSuchElementException("Student not found with ID: " + uuid));

		return null;
	}

	@Override
	public void delete(UUID uuid) {
		Students students = studentsRepository.findById(uuid)
			.orElseThrow(() -> new ResourceNotFoundException("Student not found with ID : " + uuid));
		log.info("Student delete with id {}", students.getId());
		studentsRepository.delete(students);
	}

	@Override
	public Page<StudentResponse> getByStatusPagination(Map<String, String> param, Boolean status) {

		return null;
	}

}