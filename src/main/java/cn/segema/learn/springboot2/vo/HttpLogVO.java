package cn.segema.learn.springboot2.vo;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;

import lombok.Data;

@Data
public class HttpLogVO implements Serializable {
	
	private static final long serialVersionUID = 859452469559974554L;

	@Column(name = "plugin_id")
	private BigInteger pluginId;
	
	@Column(name = "type_id")
	private Integer typeId;
	
	@Column(name = "guid")
	private BigInteger guid;
	private String guidStr;
	
	@Column(name = "flow_id")
	private BigInteger flowId;
	
	@Column(name = "sensor_id")
	private Integer sensorId;
	
	@Column(name = "log_time")
	private BigInteger logTime;
	private String logTimeStr;
	
	@Column(name = "flow_time")
	private BigInteger flowTime;
	
	@Column(name = "ip_ver")
	private Integer ipVer;
	@Column(name = "src_ip")
	private Long srcIp;
	private String srcStrIp;
	
	@Column(name = "dst_ip")
	private Long dstIp;
	private String dstStrIp;
	
	@Column(name = "src_port")
	private Integer srcPort;
	
	@Column(name = "dst_port")
	private Integer dstPort;
	
	@Column(name = "src_mac")
	private String srcMac;
	
	@Column(name = "dst_mac")
	private String dstMac;
	
	@Column(name = "proto")
	private String proto;
	
	@Column(name = "app_proto")
	private String appProto;
	
	@Column(name = "app_type")
	private String appType;

	@Column(name = "s_iso_code")
	private String sIsoCode;
	
	@Column(name = "s_country")
	private String sCountry;
	
	@Column(name = "s_city")
	private String sCity;
	
	@Column(name = "d_iso_code")
	private String dIsoCode;
	
	@Column(name = "d_country")
	private String dCountry;
	
	@Column(name = "d_city")
	private String dCity;

	@Column(name = "method")
	private String method;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "uri")
	private String uri;
	
	@Column(name = "host")
	private String host;
	
	@Column(name = "u_agnet")
	private String uAgnet;
	
	@Column(name = "req_c_type")
	private String reqCType;
	
	@Column(name = "referrer")
	private String referrer;

	@Column(name = "_post_data")
	private String postData;
	
	@Column(name = "server")
	private String server;
	
	@Column(name = "res_c_type")
	private String resCType;
	
	@Column(name = "x_cache")
	private String xCache;

	@Column(name = "via")
	private String via;
	
	@Column(name = "x_powered_by")
	private String xPoweredBy;
	
	@Column(name = "download_fileid")
	private String downloadFileId;
	
	@Column(name = "code")
	private BigInteger code;

	@Column(name = "files.file_uuid")
	private String[] fileUuid;
	
	@Column(name = "files.file_name")
	private String[] fileName;
	
	@Column(name = "files.file_len")
	private BigInteger[] fileLen;
	
	@Column(name = "files.file_type")
	private String[] fileType;


}
