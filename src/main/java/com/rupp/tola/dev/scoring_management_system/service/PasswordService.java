package com.rupp.tola.dev.scoring_management_system.service;

import com.rupp.tola.dev.scoring_management_system.DTO.UserResponseDto;

public interface PasswordService {
//	void sendForgotPasswordEmail(String email);
	UserResponseDto sendForgotPasswordEmail(String email);

//	void resetPassword(String token, String newPassword);
	UserResponseDto resetPassword(String token, String newPassword);
}
