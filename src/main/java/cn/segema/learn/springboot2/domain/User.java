package cn.segema.learn.springboot2.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("用户")
@Data
@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    
    private static final long serialVersionUID = 6593063193607142801L;
    
    @ApiModelProperty(value = "用户id")
    @Id
    @Column(name = "user_id")
    private String userId;
    
    @ApiModelProperty(value = "用户名")
    @Column(name = "user_name")
    private String userName;
    
    @ApiModelProperty(value = "昵称")
    @Column(name = "nick_name")
    private String nickName;
    
    @ApiModelProperty(value = "密码")
    @Column(name = "password")
    private String password;

    @ApiModelProperty(value = "手机号码")
    @Column(name = "mobile_number")
    private String mobileNumber;

    @ApiModelProperty(value = "性别")
    @Column(name = "gender")
    private Integer gender;
    
    @ApiModelProperty(value = "年龄")
    @Column(name = "age")
    private BigDecimal age;
    
    @Override
    public String toString() {
            return JSON.toJSONString(this,true);
    }
    
}
