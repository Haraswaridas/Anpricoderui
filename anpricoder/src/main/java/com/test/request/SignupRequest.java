package com.test.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequest {
	@NotBlank(message = "First name cannot be empty")
	@Size(min = 3, max = 20, message = "First name must be between 3 and 20 characters")
	private String firstName;

	@NotBlank(message = "Last name cannot be empty")
	@Size(min = 3, max = 20, message = "Last name must be between 3 and 20 characters")
	private String lastName;

	@NotBlank(message = "Email address is required")
	@Size(max = 50, message = "Email cannot exceed 50 characters")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be between 10 and 15 digits and can optionally start with '+'")
	private String phoneNumber;
	    
	    @NotBlank(message = "Phone number is required")
	    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number format")
	    private String parentsPhone;

	    
	    @NotBlank(message = "Password is required")
	    @Size(min = 8, message = "Password must be at least 8 characters long")
	    private String password;

	    
}
