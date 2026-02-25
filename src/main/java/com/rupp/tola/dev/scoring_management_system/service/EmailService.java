package com.rupp.tola.dev.scoring_management_system.service;

public interface EmailService {
	void sendVerificationEmail(String email, String verificationToken);

	void sendForgotPasswordEmail(String email, String resetToken);
}
