package com.test.userServices;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.test.userEntity.UserDetailsEntity;

@Service
public interface UserDetailsService {

	UserDetailsEntity createUser(UserDetailsEntity userDetails);

	Optional<UserDetailsEntity> getUserByEmail(String emailId);

	Iterable<UserDetailsEntity> getAllUsers();

	UserDetailsEntity updateUser(String emailId, UserDetailsEntity userDetails);

	void deleteUser(String emailId);

}
