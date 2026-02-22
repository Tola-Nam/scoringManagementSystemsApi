package com.rupp.tola.dev.scoring_management_system.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "upload_batches")
public class UploadBatches {
	@Id
	@UuidGenerator(style = UuidGenerator.Style.RANDOM)
	@Column(name = "upload_batch_id", columnDefinition = "uuid", updatable = false, nullable = false)
	private UUID id;

	@Column(name = "file_name", updatable = false, nullable = false)
	private String fileName;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status = Status.PENDING;

	@Column(name = "total_rows", updatable = false, nullable = false, columnDefinition = "INT DEFAULT 0)")
	private Integer totalRow = 0;

	@Column(name = "success_rows", columnDefinition = "INT DEFAULT 0", updatable = false, nullable = false)
	private Integer successRow = 0;

	@Column(name = "fail_row", columnDefinition = "INT DEFAULT 0", updatable = false, nullable = false)
	private Integer failRow;

	@Column(name = "create_at", updatable = false, nullable = false)
	private LocalDateTime createAt;
	
	@Column(name = "completed_at", updatable = false, nullable = false)
	private LocalDateTime completedAt;

	public enum Status {
		PENDING, APPROVED, REJECTED
	}
}
