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

package com.stacksurveyor.backend.validators;

import com.stacksurveyor.backend.AuthenticationErrorCode;
import com.stacksurveyor.backend.controllers.RegisterForm;
import com.stacksurveyor.backend.exceptions.UserException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    RegisterForm registerForm;

    public void validateOrThrowException() throws UserException {

        if (!isEmailValid(registerForm.getEmail())) {
            throw new UserException(AuthenticationErrorCode.EMAIL_NOT_VALID, "Email is not valid", 400);
        }

        if (isUsernameLong(registerForm.getUsername(), 16)) {
            throw new UserException(AuthenticationErrorCode.USERNAME_TOO_LONG, "Username should be less than 17 characters long", 400);
        }

        if (isPasswordShort(registerForm.getPassword(), 8)) {
            throw new UserException(AuthenticationErrorCode.PASSWORD_NOT_LONG, "Password should be atleast 8 characters", 400);
        }
    }

    public UserValidator(RegisterForm registerForm) {
        this.registerForm = registerForm;
    }

    public boolean isEmailValid(String email) {
        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    public boolean isPasswordShort(String password, int minLength) {
        return password.length() < minLength;
    }

    public boolean isUsernameLong(String username, int maxLength) {
        return username.length() > maxLength;
    }
}
