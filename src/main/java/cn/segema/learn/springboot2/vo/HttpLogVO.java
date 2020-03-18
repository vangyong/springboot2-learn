package cn.segema.learn.springboot2.vo;

import java.io.Serializable;
import java.math.BigInteger;

import lombok.Data;

@Data
public class HttpLogVO implements Serializable {
	
	private static final long serialVersionUID = 859452469559974554L;

	private BigInteger pluginId;
	
	private Integer typeId;
	
	private BigInteger flowId;
	
	private Integer sensorId;
	
	private BigInteger logTime;
	
	private BigInteger flowTime;
	
	private Integer ipVer;
	
	private Long srcIp;
	
	private Long dstIp;
	
	private Integer srcPort;
	
	private Integer dstPort;
	
	private String[] fileUuid;
	
	private String[] fileName;
	
	private BigInteger[] fileLen;
	
	private String[] fileType;


}
