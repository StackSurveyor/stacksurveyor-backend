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

import com.stacksurveyor.backend.exceptions.BaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @AllArgsConstructor
    @NoArgsConstructor
    private class JSONResponse {
        @Getter
        // Error code
        int code;

        @Getter
        // Error message
        String message;

        @Getter
        // HTTP status
        int status;
    }

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<JSONResponse> handleException(BaseException e) {
        return new ResponseEntity<JSONResponse>(new JSONResponse(e.getCode(), e.getMessage(), e.getHttpStatus()), HttpStatus.valueOf(e.getHttpStatus()));
    }

}
