package com.baizhi.entity;

import java.io.Serializable;
import java.util.Objects;

public class ErrorMsg implements Serializable {
    private String error;
    private String error_msg;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorMsg errorMsg = (ErrorMsg) o;
        return Objects.equals(error, errorMsg.error) &&
                Objects.equals(error_msg, errorMsg.error_msg);
    }

    @Override
    public int hashCode() {

        return Objects.hash(error, error_msg);
    }

    @Override
    public String toString() {
        return "ErrorMsg{" +
                "error='" + error + '\'' +
                ", error_msg='" + error_msg + '\'' +
                '}';
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public ErrorMsg(String error, String error_msg) {

        this.error = error;
        this.error_msg = error_msg;
    }

    public ErrorMsg() {

    }
}
