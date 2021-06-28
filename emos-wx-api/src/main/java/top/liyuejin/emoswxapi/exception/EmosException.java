package top.liyuejin.emoswxapi.exception;

import lombok.Getter;
import lombok.Setter;

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
