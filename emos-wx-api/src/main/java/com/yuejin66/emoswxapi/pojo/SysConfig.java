package com.yuejin66.emoswxapi.pojo;

import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yuejin66
 */
@Getter
@Setter
public class SysConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 参数名
     */
    private String paramKey;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 备注
     */
    private String remark;
}