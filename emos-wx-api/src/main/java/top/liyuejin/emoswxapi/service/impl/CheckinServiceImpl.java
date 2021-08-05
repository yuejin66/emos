package top.liyuejin.emoswxapi.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import top.liyuejin.emoswxapi.config.SystemConstants;
import top.liyuejin.emoswxapi.dao.TbCheckinDao;
import top.liyuejin.emoswxapi.dao.TbHolidaysDao;
import top.liyuejin.emoswxapi.dao.TbWorkdayDao;
import top.liyuejin.emoswxapi.enums.DayEnum;
import top.liyuejin.emoswxapi.service.CheckinService;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author tom lee
 */
@Service
@Scope("prototype")
public class CheckinServiceImpl implements CheckinService {

    private static final Logger log = LoggerFactory.getLogger(CheckinServiceImpl.class);

    @Resource
    private TbCheckinDao checkinDao;

    @Resource
    private TbWorkdayDao workdayDao;

    @Resource
    private TbHolidaysDao holidaysDao;

    @Resource
    private SystemConstants systemConstants;

    @Override
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
