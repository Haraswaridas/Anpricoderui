package com.test.userEntity;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserDetailsEntityDao extends CrudRepository<UserDetailsEntity, Long> {

	Optional<UserDetailsEntity> findById(Long id);
	UserDetailsEntity findByEmail(String email);
	UserDetailsEntity findByPhone(String phone);
	void deleteById(Long id);
	

}
