package cn.segema.learn.springboot2.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO  implements Serializable {
	
	private static final long serialVersionUID = 559547291188482705L;

	private String userId;

	private String userName;

	private String nickName;

	private Integer gender;

	private String mobileNumber;

	private String password;

	private BigDecimal age;
}