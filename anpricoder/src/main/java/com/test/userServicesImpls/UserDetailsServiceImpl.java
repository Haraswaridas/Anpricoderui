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
    private  UserDetailsEntityDao userDetailsEntityDao;
	
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
	

}
