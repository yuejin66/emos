package top.liyuejin.emoswxapi.enums;

/**
 * @author tom lee
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
