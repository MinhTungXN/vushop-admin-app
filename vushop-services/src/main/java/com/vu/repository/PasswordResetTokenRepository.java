package com.vu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vu.entity.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer>{

	PasswordResetToken findByToken(String token);
}
