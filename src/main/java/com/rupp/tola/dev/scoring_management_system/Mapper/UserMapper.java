package com.rupp.tola.dev.scoring_management_system.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rupp.tola.dev.scoring_management_system.DTO.RegistrationRequestDto;
import com.rupp.tola.dev.scoring_management_system.DTO.UserResponseDto;
import com.rupp.tola.dev.scoring_management_system.entity.Users;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "verificationToken", ignore = true)
	@Mapping(target = "verified", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "username", source = "username")
	Users touser(RegistrationRequestDto dto);

	@Mapping(target = "id", source = "id")
	@Mapping(target = "username", source = "username")
	@Mapping(target = "email", source = "email")
	@Mapping(target = "verified", source = "verified")
	@Mapping(target = "verificationToken", source = "verificationToken")
	UserResponseDto toResponseDto(Users user);
}