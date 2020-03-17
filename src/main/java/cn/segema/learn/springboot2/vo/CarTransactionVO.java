//package cn.segema.learn.springboot2.vo;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//
//import lombok.Data;
//
//@Data
//@Document(indexName = "car", type = "transaction", shards = 1, replicas = 0)
//public class CarTransactionVO implements Serializable {
//	
//	private static final long serialVersionUID = 859452469559974554L;
//
//	@Id
//	private String id;
//
//	private Integer price;
//
//	private String color;
//
//	private String make;
//
//	// @Field(type = FieldType.Date, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
//	// @JsonFormat (shape = JsonFormat.Shape.STRING, pattern
//	// ="yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
//	private Date sold;
//
//}
