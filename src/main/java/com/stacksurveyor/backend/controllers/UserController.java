//	Copyright (C) 2021  StackSurveyor
//
//	This program is free software: you can redistribute it and/or modify
//	it under the terms of the GNU General Public License as published by
//	the Free Software Foundation, either version 3 of the License, or
//	(at your option) any later version.
//
//	This program is distributed in the hope that it will be useful,
//	but WITHOUT ANY WARRANTY; without even the implied warranty of
//	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//	GNU General Public License for more details.
//
//	You should have received a copy of the GNU General Public License
//	along with this program.  If not, see <https://www.gnu.org/licenses/>.

package com.stacksurveyor.backend.controllers;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.stacksurveyor.backend.exceptions.UserException;
import com.stacksurveyor.backend.models.User;
import com.stacksurveyor.backend.repositories.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Data
class RegisterForm {
    private String username;
    private String email;
    private String password;
}

/*
* Response status codes:
*   1: Email already exists
*   2: Password is less than 8 characters
*   3: Invalid credentials
* */

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @PostMapping("/users/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterForm payload) throws UserException {
        // API endpoint used to register new users
        User existingUser = userRepository.findByEmail(payload.getEmail());
        if (!Objects.isNull(existingUser)) {
            throw new UserException(1, "Email already exists", 400);
        }

        // Using Bcrypt's password encoder to hash the password
        String encodedPassword = passwordEncoder.encode(payload.getPassword());

        // Saving user to database
        User newUser = userRepository.save(new User(Uuids.timeBased(), payload.getUsername(), payload.getEmail(), encodedPassword));

        // Returning HTTP response with newly created user object
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
