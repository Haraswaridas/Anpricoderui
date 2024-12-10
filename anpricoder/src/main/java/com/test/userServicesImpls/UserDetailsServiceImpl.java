package com.test.userServicesImpls;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.request.LoginRequest;
import com.test.request.SignupRequest;
import com.test.userEntity.UserDetailsEntity;
import com.test.userEntity.UserDetailsEntityDao;
import com.test.userServices.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
    private  UserDetailsEntityDao userDetailsEntityDao;
	
//	private final PasswordEncoder passwordEncoder;
	
	private final Map<String, String> userStore = new HashMap<>(); // Simulating user database
    private final Map<String, String> activeTokens = new HashMap<>(); // Simulating token store
    
//    public UserDetailsServiceImpl(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
    
	public UserDetailsServiceImpl(UserDetailsEntityDao userDetailsEntityDao) {
        this.userDetailsEntityDao = userDetailsEntityDao;
    }

    @Override
    public UserDetailsEntity createUser(UserDetailsEntity user) {
        return userDetailsEntityDao.save(user);
    }

    @Override
    public UserDetailsEntity updateUser(Long id, UserDetailsEntity user) {
        Optional<UserDetailsEntity> existingUser = userDetailsEntityDao.findById(id);
        if (existingUser.isPresent()) {
            UserDetailsEntity updatedUser = existingUser.get();
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPhone(user.getPhone());
            updatedUser.setParentsPhone(user.getParentsPhone());
            updatedUser.setPassword(user.getPassword());
            return userDetailsEntityDao.save(updatedUser);
        }
        throw new RuntimeException("User not found with ID: " + id);
    }
    @Override
    public Optional<UserDetailsEntity> getUserById(Long id) {
        return userDetailsEntityDao.findById(id);
    }

    @Override
    public Optional<UserDetailsEntity> findByGmail(String email) {
        return Optional.ofNullable(userDetailsEntityDao.findByEmail(email));
    }

    @Override
    public void deleteUser(Long id) {
        userDetailsEntityDao.deleteById(id);
    }

	
	 @Override
	    public Iterable<UserDetailsEntity> getAllUsers() {
	        return userDetailsEntityDao.findAll();
	    }
//	 @Override
//	    public String registerUser(SignupRequest signupRequest) {
//	        if (userStore.containsKey(signupRequest.getEmail())) {
//	            throw new RuntimeException("Email already registered.");
//	        }
//	        userStore.put(signupRequest.getEmail(), passwordEncoder.encode(signupRequest.getPassword()));
//	        return "User registered successfully.";
//	    }
//	 @Override
//	    public String authenticateUser(LoginRequest loginRequest) {
//	        String storedPassword = userStore.get(loginRequest.getEmail());
//	        if (storedPassword == null || !passwordEncoder.matches(loginRequest.getPassword(), storedPassword)) {
//	            throw new RuntimeException("Invalid email or password.");
//	        }
//	        String token = generateToken(loginRequest.getEmail());
//	        activeTokens.put(token, loginRequest.getEmail());
//	        return token;
//	    }
	 @Override
	    public void logoutUser(String token) {
	        if (!activeTokens.containsKey(token)) {
	            throw new RuntimeException("Invalid token.");
	        }
	        activeTokens.remove(token);
	    }

	    private String generateToken(String email) {
	        return email + "_token_" + System.currentTimeMillis(); // Simple token generation logic
	    }
	

}
