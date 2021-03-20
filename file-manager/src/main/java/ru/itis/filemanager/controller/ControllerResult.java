package ru.itis.filemanager.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ControllerResult {

    private boolean success = true;
    private String message;
    private String result;

    public ControllerResult() {

    }

    public ControllerResult(boolean success, String message) {
        this.setSuccess(success);
        this.setMessage(message);
    }

    public ControllerResult(boolean success, String message, String result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public static ControllerResult successResult(String message) {
        ControllerResult result = new ControllerResult();
        result.success = true;
        result.message = message;
        return result;
    }

    public static ControllerResult failResult(String message) {
        ControllerResult result = new ControllerResult();
        result.success = false;
        result.message = message;
        return result;
    }


}
