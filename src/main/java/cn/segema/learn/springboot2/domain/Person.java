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
@Table(name = "tb_person")
public class Person implements Serializable {
    
    private static final long serialVersionUID = 6593063193607142801L;
    
    @ApiModelProperty(value = "人员id")
    @Id
    @Column(name = "person_id")
    private String personId;
    
    @ApiModelProperty(value = "人名")
    @Column(name = "person_name")
    private String personName;
    
    @ApiModelProperty(value = "地址")
    @Column(name = "address_detail")
    private String addressDetail;
    
    @ApiModelProperty(value = "年龄")
    @Column(name = "age")
    private BigDecimal age;
    
    @Override
    public String toString() {
            return JSON.toJSONString(this,true);
    }
    
}
