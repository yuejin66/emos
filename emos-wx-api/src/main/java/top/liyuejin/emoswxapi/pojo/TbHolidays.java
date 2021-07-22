package top.liyuejin.emoswxapi.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * tb_holidays
 * @author tom lee
 */
@Data
public class TbHolidays implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 日期
     */
    private Date date;

    private static final long serialVersionUID = 1L;
}