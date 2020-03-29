package cn.segema.learn.springboot2.vo;

import java.math.BigInteger;

import lombok.Data;

@Data
public class UserPersonVO {

    private String userId;

    private String userName;

    private String nickName;

    private String personId;

    private String personName;

    public UserPersonVO(String userId, String userName, String nickName, String personId,
        String personName) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.nickName = nickName;
        this.personId = personId;
        this.personName = personName;
    }

}
