package com.order.resp;

public class BrandResp<T> {
    private Boolean success = true;
    private String message;
    private T content;

    @Override
    public String toString() {
        return "BrandResp{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
