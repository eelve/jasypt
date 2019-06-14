package com.eelve.springboot.jasypt.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @ClassName UserDetail
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2019/6/14 11:10
 * @Version 1.0
 **/
@Data
public class UserDetail {
    private long id;
    @NotEmpty(message="姓名不能为空")
    private String userName;
    @NotEmpty(message="密码不能为空")
    @Length(min=6,message="密码长度不能小于6位")
    private String passWord;
    @Max(value = 100, message = "年龄不能大于100岁")
    @Min(value= 18 ,message= "必须年满18岁！" )
    private int age;
}
