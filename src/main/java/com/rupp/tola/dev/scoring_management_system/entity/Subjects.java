package com.rupp.tola.dev.scoring_management_system.entity;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subjects")
public class Subjects {
	@Id
	@UuidGenerator(style = UuidGenerator.Style.RANDOM)
	@Column(name = "subject_id", columnDefinition = "uuid", updatable = false, nullable = false)
	private UUID id;

	@Column(name = "subject_code", updatable = false, nullable = false)
	private String subjectCode;

	@Column(name = "subject_name", updatable = false, nullable = false)
	private String subjectName;
}
