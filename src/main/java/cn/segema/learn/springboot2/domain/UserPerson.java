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

@ApiModel("用户人员关系")
@Data
@Entity
@Table(name = "tb_user_person")
public class UserPerson implements Serializable {
    
    private static final long serialVersionUID = 6593063193607142801L;
    
    @ApiModelProperty(value = "用户人员关系id")
    @Id
    @Column(name = "user_person_id")
    private String userPersonId;
    
    @ApiModelProperty(value = "用户id")
    @Column(name = "user_id")
    private String userId;
    
    @ApiModelProperty(value = "人员关系id")
    @Column(name = "person_id")
    private String personId;
    
    
    @Override
    public String toString() {
            return JSON.toJSONString(this,true);
    }
    
}
