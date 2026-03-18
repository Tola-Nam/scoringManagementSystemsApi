package com.rupp.tola.dev.scoring_management_system.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class StudentsDTO {
	private String studentCode;
	private String firstName;
	private String LastNam;
	private UUID classId;
	private Boolean status = false;
}
