package com.yuejin66.emoswxapi.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuejin66
 */
@Getter
@Setter
public class Holidays implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 日期
     */
    private Date date;

}