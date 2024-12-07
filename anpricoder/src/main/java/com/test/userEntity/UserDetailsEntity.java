package com.test.userEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserDetailsEntity {
	 @Id
	    private String emailId; // Primary key

	    private String firstName;
	    private String lastName;
	    private String phone;
	    private String parentsPhone;
	    private String password;
	    private String confirmPassword;


}
