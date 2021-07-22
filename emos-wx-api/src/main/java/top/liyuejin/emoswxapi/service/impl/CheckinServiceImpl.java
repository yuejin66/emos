package top.liyuejin.emoswxapi.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import top.liyuejin.emoswxapi.config.SystemConstants;
import top.liyuejin.emoswxapi.dao.TbCheckinDao;
import top.liyuejin.emoswxapi.dao.TbHolidaysDao;
import top.liyuejin.emoswxapi.dao.TbWorkdayDao;
import top.liyuejin.emoswxapi.service.CheckinService;

import javax.annotation.Resource;

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
        return null;
    }
}
