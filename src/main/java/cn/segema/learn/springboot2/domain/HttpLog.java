package cn.segema.learn.springboot2.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("http日志")
@Data
@Entity
@Table(name = "tb_http_log")
public class HttpLog implements Serializable {
    
    private static final long serialVersionUID = 6593063193607142801L;
    
    @ApiModelProperty(value = "日志id")
    @Id
    @Column(name = "guid")
    private String guid;
    
    @ApiModelProperty(value = "源ip")
    @Column(name = "src_ip")
    private String srcIp;
    
    @ApiModelProperty(value = "源mac")
    @Column(name = "src_mac")
    private String srcMac;
    
    @ApiModelProperty(value = "目的ip")
    @Column(name = "dst_ip")
    private String dstIp;

    @ApiModelProperty(value = "目的mac")
    @Column(name = "dst_mac")
    private String dstMac;

    @ApiModelProperty(value = "文件大小")
    @Column(name = "file_length")
    private Integer fileLength;
    
    @ApiModelProperty(value = "文件内容")
    @Column(name = "file_content")
    private Byte[] fileContent;
    
    @ApiModelProperty(value = "写入时间")
    @Column(name = "create_time")
    private BigDecimal createTime;
    
    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_time")
    private Date updateTime;
    
    @Override
    public String toString() {
            return JSON.toJSONString(this,true);
    }
    
}
