package top.liyuejin.emoswxapi.form;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

@ApiModel
public class LoginForm {

    @NotBlank(message = "临时授权码不能为空")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
