package cn.segema.learn.springboot2.vo;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private BigInteger id;

	private String name;

	private String password;
}