package com.rupp.tola.dev.scoring_management_system.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {
	@Id
	@UuidGenerator(style = UuidGenerator.Style.RANDOM)
	@Column(name = "user_id", columnDefinition = "uuid", updatable = false, nullable = false)
	private UUID id;

	@Column(name = "user_name", updatable = false, nullable = false)
	private String name;

	@Column(name = "password_hash", updatable = false, nullable = false)
	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Roles roles;

	@Column(name = "created_at", updatable = false, nullable = false)
	private LocalDateTime date;

}
