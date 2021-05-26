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

package com.stacksurveyor.backend.utils.errors;

/*
 * Contains Error codes for authentication related requests.
 * */
public enum AuthenticationErrorCodes {
    EMAIL_NOT_VALID(0),
    EMAIL_ALREADY_EXISTS(1),
    PASSWORD_NOT_LONG(2),
    USERNAME_TOO_LONG(3),
    WRONG_CREDENTIALS(4),
    INVALID_INPUT(5);

    private final int errorCode;

    AuthenticationErrorCodes(int errCode) {
        this.errorCode = errCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
