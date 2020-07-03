package cn.segema.learn.springboot2.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

/**
* @description 电影节点
* @author wangyong
* @createDate 2020/07/03
*/
@Data
@NodeEntity
public class MovieNode {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String released;

    public MovieNode() {

    }

    public MovieNode(String title, String released) {
        this.title = title;
        this.released = released;
    }
}
