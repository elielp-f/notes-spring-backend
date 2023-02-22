package com.notes.notes.expeptions;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorTDO {
    public String title;
    public String details;
    public int statusCode;
    public String errorType;
    public String errorCode;
    public String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a z Z"));

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public static ErrorDTOBuilder builder(){
        return new ErrorDTOBuilder();
    }

    public ErrorTDO(String title, String details, int statusCode, String errorType, String errorCode) {
        this.title = title;
        this.details = details;
        this.statusCode = statusCode;
        this.errorType = errorType;
        this.errorCode = errorCode;
    }


}
