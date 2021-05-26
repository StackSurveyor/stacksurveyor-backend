package com.stacksurveyor.backend.exceptions;

import com.stacksurveyor.backend.utils.SurveyErrorCodes;

public class SurveyException extends BaseException {
    public SurveyException(final SurveyErrorCodes code, final String message, final int HttpStatus) {
        super(code.getErrorCode(), message, HttpStatus);
    }
}