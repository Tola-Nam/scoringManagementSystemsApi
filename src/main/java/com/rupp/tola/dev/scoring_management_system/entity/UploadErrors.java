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
import lombok.Data;

@Data
@Entity
@Table(name = "upload_errors")
public class UploadErrors {
	@Id
	@UuidGenerator(style = UuidGenerator.Style.RANDOM)
	@Column(name = "upload_error_id", columnDefinition = "uuid", updatable = false, nullable = false)
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "upload_batch_id")
	private UploadBatches batches;

	@Column(name = "row_name", updatable = false, nullable = false)
	private Integer rowNumber;

	@Column(name = "error_message", updatable = false, nullable = false)
	private String errorMessage;

	@Column(name = "raw_data", columnDefinition = "jsonb")
	private String rawData;
	@Column(name = "create_at")
	private LocalDateTime createAt;
}
