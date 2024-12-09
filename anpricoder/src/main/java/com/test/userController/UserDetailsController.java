package com.test.userController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.userEntity.UserDetailsEntity;
import com.test.userServices.UserDetailsService;

@RestController
public class UserDetailsController {
	 @Autowired
	    private UserDetailsService userDetailsService;
	 
	 public UserDetailsController(UserDetailsService userDetailsService) {
	        this.userDetailsService = userDetailsService;
	    }

	    // Create User
	    @PostMapping
	    public ResponseEntity<UserDetailsEntity> createUser(@RequestBody UserDetailsEntity user) {
	        UserDetailsEntity createdUser = userDetailsService.createUser(user);
	        return ResponseEntity.ok(createdUser);
	    }

	    // Get User by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<UserDetailsEntity> getUserById(@PathVariable Long id) {
	        Optional<UserDetailsEntity> user = userDetailsService.getUserById(id);
	        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }
	    // Find User by Gmail
	    @GetMapping("/findByGmail")
	    public ResponseEntity<UserDetailsEntity> getUserByGmail(@RequestParam String email) {
	        Optional<UserDetailsEntity> user = userDetailsService.findByGmail(email);
	        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    // Update User
	    @PutMapping("/{id}")
	    public ResponseEntity<UserDetailsEntity> updateUser(@PathVariable Long id, @RequestBody UserDetailsEntity user) {
	        try {
	            UserDetailsEntity updatedUser = userDetailsService.updateUser(id, user);
	            return ResponseEntity.ok(updatedUser);
	        } catch (RuntimeException ex) {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    // Delete User
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
	        userDetailsService.deleteUser(id);
	        return ResponseEntity.noContent().build();
	    }

	    // Get All Users
	    @GetMapping
	    public ResponseEntity<Iterable<UserDetailsEntity>> getAllUsers() {
	        return ResponseEntity.ok(userDetailsService.getAllUsers());
	    }
	 

}
