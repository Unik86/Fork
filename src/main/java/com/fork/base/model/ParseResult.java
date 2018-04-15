package com.fork.base.model;

public class ParseResult {

    private String bookMakerName;
    private String resultMessage;

    public ParseResult(String bookMakerName, String resultMessage) {
        this.bookMakerName = bookMakerName;
        this.resultMessage = resultMessage;
    }

    public String getBookMakerName() {
        return bookMakerName;
    }

    public String getResultMessage() {
        return resultMessage;
    }
}
