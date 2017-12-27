package com.mickaelbrenoit.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordUtils.class);
	
	public static String hashPassword(String password) {
		if (password != null) {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			return passwordEncoder.encode(password);
		}
		return null;
	}
	
	public static boolean decodePassword(String passwordUser, String passwordDatabase) {
		if(passwordUser != null && passwordDatabase != null) {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			boolean result = passwordEncoder.matches(passwordUser, passwordDatabase);
			return result;
		}
		return false;
	}
	
}
