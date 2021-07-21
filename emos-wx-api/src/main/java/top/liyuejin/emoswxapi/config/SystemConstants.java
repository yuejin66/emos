package top.liyuejin.emoswxapi.config;

import org.springframework.stereotype.Component;

@Component
public class SystemConstants {

    public String attendanceStartTime;

    public String attendanceTime;

    public String attendanceEndTime;

    public String closingStartTime;

    public String closingTime;

    public String closingEndTime;

    public String getAttendanceStartTime() {
        return attendanceStartTime;
    }

    public void setAttendanceStartTime(String attendanceStartTime) {
        this.attendanceStartTime = attendanceStartTime;
    }

    public String getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(String attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public String getAttendanceEndTime() {
        return attendanceEndTime;
    }

    public void setAttendanceEndTime(String attendanceEndTime) {
        this.attendanceEndTime = attendanceEndTime;
    }

    public String getClosingStartTime() {
        return closingStartTime;
    }

    public void setClosingStartTime(String closingStartTime) {
        this.closingStartTime = closingStartTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getClosingEndTime() {
        return closingEndTime;
    }

    public void setClosingEndTime(String closingEndTime) {
        this.closingEndTime = closingEndTime;
    }
}
