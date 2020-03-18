package cn.segema.learn.springboot2.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class CarTransactionVO implements Serializable {

	private static final long serialVersionUID = 859452469559974554L;

	private String id;

	private Integer price;

	private String color;

	private String make;

	// @Field(type = FieldType.Date, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	// @JsonFormat (shape = JsonFormat.Shape.STRING, pattern
	// ="yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	private Date sold;

}
