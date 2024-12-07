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
import org.springframework.web.bind.annotation.RestController;

import com.test.userEntity.UserDetailsEntity;
import com.test.userServices.UserDetailsService;

@RestController
public class UserDetailsController {
	 @Autowired
	    private UserDetailsService userDetailsService;
	 @PostMapping("/save")
	    public ResponseEntity<UserDetailsEntity> createUser(@RequestBody UserDetailsEntity userDetails) {
	        UserDetailsEntity createdUser = userDetailsService.createUser(userDetails);
	        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	    }
	 @GetMapping("/show/{emailId}")
	    public ResponseEntity<Optional<UserDetailsEntity>> getUserByEmail(@PathVariable String emailId) {
	        Optional<UserDetailsEntity> user = userDetailsService.getUserByEmail(emailId);
	        if (user.isPresent()) {
	            return new ResponseEntity<>(user, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	 @GetMapping("/show")
	    public ResponseEntity<Iterable<UserDetailsEntity>> getAllUsers() {
	        Iterable<UserDetailsEntity> users = userDetailsService.getAllUsers();
	        return new ResponseEntity<>(users, HttpStatus.OK);
	    }
	 @PutMapping("/update/{emailId}")
	    public ResponseEntity<UserDetailsEntity> updateUser(
	            @PathVariable String emailId,
	            @RequestBody UserDetailsEntity userDetails) {
	        try {
	            UserDetailsEntity updatedUser = userDetailsService.updateUser(emailId, userDetails);
	            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	        } catch (RuntimeException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	 @DeleteMapping("/delete/{emailId}")
	    public ResponseEntity<Void> deleteUser(@PathVariable String emailId) {
	        try {
	            userDetailsService.deleteUser(emailId);
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	 

}
