package com.yuejin66.emoswxapi.form;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ApiModel
public class RegisterForm {

    @NotBlank(message = "注册码不能为空")
    @Pattern(regexp = "^[0-9]{6}$", message = "注册码必须为6位数字")
    private String registerCode;

    @NotBlank(message = "微信临时登陆凭证不能为空")
    private String code;

    @NotBlank(message = "昵称不能为空")
    private String nickName;

    @NotBlank(message = "头像不能空")
    private String photo;

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
