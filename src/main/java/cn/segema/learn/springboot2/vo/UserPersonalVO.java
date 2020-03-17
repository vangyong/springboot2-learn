package cn.segema.learn.springboot2.vo;

import java.math.BigInteger;

import lombok.Data;

@Data
public class UserPersonalVO {

    private BigInteger userId;

    private String userName;

    private String nickName;

    private BigInteger personalId;

    private String personalName;

    public UserPersonalVO(BigInteger userId, String userName, String nickName, BigInteger personalId,
        String personalName) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.nickName = nickName;
        this.personalId = personalId;
        this.personalName = personalName;
    }

}
