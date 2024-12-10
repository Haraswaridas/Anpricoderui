package com.test.userServices;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.test.request.LoginRequest;
import com.test.request.SignupRequest;
import com.test.userEntity.UserDetailsEntity;

@Service
public interface UserDetailsService {

	UserDetailsEntity createUser(UserDetailsEntity user);

    UserDetailsEntity updateUser(Long id, UserDetailsEntity user);

    Optional<UserDetailsEntity> getUserById(Long id);

    Optional<UserDetailsEntity> findByGmail(String email);

    void deleteUser(Long id);

    Iterable<UserDetailsEntity> getAllUsers();
//    String registerUser(SignupRequest signupRequest);
//    String authenticateUser(LoginRequest loginRequest);
    void logoutUser(String token);

	

}
