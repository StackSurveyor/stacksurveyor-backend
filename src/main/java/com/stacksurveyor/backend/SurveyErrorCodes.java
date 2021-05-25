package com.stacksurveyor.backend;

public enum SurveyErrorCodes {
    SURVEY_NOT_EXIST(0);

    private final int errCode;

    SurveyErrorCodes(int e) {
        this.errCode = e;
    }

    public int getErrorCode() {
        return errCode;
    }
}
