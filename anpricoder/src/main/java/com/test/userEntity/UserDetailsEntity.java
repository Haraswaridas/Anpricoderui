package com.test.userEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class UserDetailsEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	    
	@Column(name = "email", nullable = false, unique = true)
	private String email; 
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "phone", nullable = false, unique = true)
	private String phone;
	
	@Column(name = "parents_phone", nullable = false)
	private String parentsPhone;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Transient
	private String confirmPassword;

	

}
