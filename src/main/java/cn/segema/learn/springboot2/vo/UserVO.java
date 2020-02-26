package cn.segema.learn.springboot2.vo;

import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
	private BigInteger userId;

	private String userName;

	private String nickName;

	private Integer gender;

	private String mobileNumber;

	private String password;

	private BigDecimal age;
}