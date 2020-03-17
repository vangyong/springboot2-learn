//package cn.segema.learn.springboot2.vo;
//package cn.segema.cloud.demo.vo;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//import lombok.Data;
//
//@Data
//@Document(indexName = "car", type = "transaction", shards = 1, replicas = 0, refreshInterval = "-1")
//public class DemoCarTransactionVO implements Serializable {
//    private static final long serialVersionUID = 859452469559974554L;
//
//    @Id
//    private String id;
//
//    private Integer price;
//
//    private String color;
//
//    private String make;
//
//    @Field(type = FieldType.Date, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
//    // @JsonFormat (shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
//    private Date sold;
//
//}
