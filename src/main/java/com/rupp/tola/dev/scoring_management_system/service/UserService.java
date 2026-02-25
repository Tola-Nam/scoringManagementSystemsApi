package com.rupp.tola.dev.scoring_management_system.service;

import com.rupp.tola.dev.scoring_management_system.DTO.RegistrationRequestDto;
import com.rupp.tola.dev.scoring_management_system.DTO.UserResponseDto;

public interface UserService {

	UserResponseDto register(RegistrationRequestDto request);

//    void verifyEmail(String token);
	UserResponseDto verifyEmail(String token);
}
