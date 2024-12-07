package com.test.userServicesImpls;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.userEntity.UserDetailsEntity;
import com.test.userEntity.UserDetailsEntityDao;
import com.test.userServices.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
    private UserDetailsEntityDao userDetailsEntityDao;
	
	@Override
	public UserDetailsEntity createUser(UserDetailsEntity userDetails) {
		return userDetailsEntityDao.save(userDetails);
	}

	@Override
	public Optional<UserDetailsEntity> getUserByEmail(String emailId) {
		return userDetailsEntityDao.findById(emailId);
	}

	@Override
	public Iterable<UserDetailsEntity> getAllUsers() {
		return userDetailsEntityDao.findAll();
	}

	@Override
	public UserDetailsEntity updateUser(String emailId, UserDetailsEntity userDetails) {
		 Optional<UserDetailsEntity> existingUserOpt = userDetailsEntityDao.findById(emailId);

	        if (existingUserOpt.isPresent()) {
	            UserDetailsEntity existingUser = existingUserOpt.get();

	            // Update fields
	            existingUser.setFirstName(userDetails.getFirstName());
	            existingUser.setLastName(userDetails.getLastName());
	            existingUser.setPhone(userDetails.getPhone());
	            existingUser.setParentsPhone(userDetails.getParentsPhone());
	            existingUser.setPassword(userDetails.getPassword());
	            existingUser.setConfirmPassword(userDetails.getConfirmPassword());

	            return userDetailsEntityDao.save(existingUser);
	        } else {
	            throw new RuntimeException("User not found with email: " + emailId);
	        }
	}

	@Override
	public void deleteUser(String emailId) {
		userDetailsEntityDao.deleteById(emailId);
	}
	

}
