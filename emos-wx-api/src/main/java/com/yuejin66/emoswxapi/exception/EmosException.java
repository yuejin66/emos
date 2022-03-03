package com.yuejin66.emoswxapi.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 业务异常
 *
 * @author yuejin66
 */
@Getter
@Setter
public class EmosException extends RuntimeException{

    private int code = 500;

    private String msg;

    public EmosException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
