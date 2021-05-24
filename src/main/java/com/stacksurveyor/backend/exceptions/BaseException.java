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

package com.stacksurveyor.backend.exceptions;

import lombok.Getter;

public class BaseException extends Exception{
    @Getter
    private final int code;
    @Getter
    private final String message;
    @Getter
    private final int HttpStatus;

    public BaseException(final int code, final String message, final int HttpStatus) {
        this.code = code;
        this.message = message;
        this.HttpStatus = HttpStatus;
    }

}
