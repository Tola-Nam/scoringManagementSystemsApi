package com.rupp.tola.dev.scoring_management_system;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rupp.tola.dev.scoring_management_system.Mapper.UserMapper;
import com.rupp.tola.dev.scoring_management_system.repository.UsersRepository;
import com.rupp.tola.dev.scoring_management_system.service.EmailService;
import com.rupp.tola.dev.scoring_management_system.service.util.JwtTokenUtil;

@SpringBootTest(properties = "app.cors.allowed-origins=http://localhost:4200")
class ScoringManagementSystemApplicationTests {

	@MockBean
	private UsersRepository userRepository;

	@MockBean
	private PasswordEncoder passwordEncoder;

	@MockBean
	private EmailService emailService;

	@MockBean
	private UserMapper userMapper;

	@MockBean
	private JwtTokenUtil jwtTokenUtil;

	@MockBean
	private com.rupp.tola.dev.scoring_management_system.service.PasswordService passwordService; // <<< Add this

	@Test
	void contextLoads() {
	}
}
