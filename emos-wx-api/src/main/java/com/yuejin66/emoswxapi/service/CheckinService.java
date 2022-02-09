package com.yuejin66.emoswxapi.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.yuejin66.emoswxapi.config.system.SystemConstants;
import com.yuejin66.emoswxapi.dao.CheckinMapper;
import com.yuejin66.emoswxapi.dao.HolidaysMapper;
import com.yuejin66.emoswxapi.dao.WorkdayMapper;
import com.yuejin66.emoswxapi.enums.DayEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author yuejin66
 */
@Service
@Scope("prototype")
public class CheckinService {

    private static final Logger log = LoggerFactory.getLogger(CheckinService.class);

    @Resource
    private CheckinMapper checkinDao;

    @Resource
    private WorkdayMapper workdayDao;

    @Resource
    private HolidaysMapper holidaysDao;

    @Resource
    private SystemConstants systemConstants;

    public String validCanCheckIn(int userId, String date) {
        var b1 = holidaysDao.queryTodayIsHolidays() != null;
        var b2 = workdayDao.queryTodayIsWorkday() != null;

        String day = null;
        if (DateUtil.date().isWeekend()) {
            day = DayEnum.HOLIDAYS.getMsg();
        }

        if (b1) {
            day = DayEnum.HOLIDAYS.getMsg();
        } else if (b2) {
            day = DayEnum.WORK_DAY.getMsg();
        }

        if (DayEnum.HOLIDAYS.getMsg().equals(day)) {
            return "节假日不需要考勤";
        } else {
            DateTime now = DateUtil.date();
            String startTime = DateUtil.today() + "" + systemConstants.attendanceStartTime;
            String endTime = DateUtil.today() + "" + systemConstants.attendanceEndTime;
            DateTime attendanceStart = DateUtil.parse(startTime);
            DateTime attendanceEnd = DateUtil.parse(endTime);
            if (now.isBefore(attendanceStart)) {
                return "还没到上班考勤开始时间";
            } else if (now.isAfter(attendanceEnd)) {
                return "超过了上班考勤结束时间";
            } else {
                HashMap<String, Object> map = new HashMap<>();
                map.put("userId", userId);
                map.put("date", date);
                map.put("startTime", startTime);
                map.put("endTime", endTime);
                var b3 = checkinDao.haveCheckin(map) != null;
                return b3 ? "今日已经考勤，不用重复考勤" : "可以考勤";
            }
        }
    }
}
