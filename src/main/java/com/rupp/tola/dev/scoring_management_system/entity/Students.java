package com.rupp.tola.dev.scoring_management_system.entity;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Students {
	@Id
	@UuidGenerator(style = UuidGenerator.Style.RANDOM)
	@Column(name = "student_id", columnDefinition = "uuid", updatable = false, nullable = false)
	private UUID id;

	@Column(name = "student_code", updatable = false, nullable = false)
	private String studentCode;

	@Column(name = "name", updatable = false, nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "class_id")
	private Classes classes;

	private Boolean status;
}
