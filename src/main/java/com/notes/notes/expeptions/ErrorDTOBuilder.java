package com.notes.notes.expeptions;

public class ErrorDTOBuilder {
    public String title;
    public String details;
    public int statusCode;
    public String errorType;
    public String errorCode;

    public ErrorDTOBuilder withTitle(final String title) {
        this.title = title;
        return this;
    }

    public ErrorDTOBuilder withDetails(final String details) {
        this.details = details;
        return this;
    }

    public ErrorDTOBuilder withStatusCode(final int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public ErrorDTOBuilder withErrorType(final String errorType) {
        this.errorType = errorType;
        return this;
    }

    public ErrorDTOBuilder withErrorCode(final String errorCode) {
        this.errorType = errorCode;
        return this;
    }

    public ErrorTDO build() {
        return new ErrorTDO(this.title, this.details, this.statusCode, this.errorType, this.errorCode);
    }
}
