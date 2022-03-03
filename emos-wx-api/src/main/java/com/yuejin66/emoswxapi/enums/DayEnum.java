package com.yuejin66.emoswxapi.enums;

/**
 * @author yuejin66
 */
public enum DayEnum {

    WORK_DAY("工作日"),

    HOLIDAYS("节假日"),

    ;

    String msg;

    DayEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
