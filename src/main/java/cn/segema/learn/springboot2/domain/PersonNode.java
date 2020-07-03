package cn.segema.learn.springboot2.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import lombok.Data;

/**
* @description 人员节点
* @author wangyong
* @createDate 2020/07/03
*/
@Data
@NodeEntity
public class PersonNode {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String born;

    public PersonNode() {// 从 Neo4j API 2.0.5开始需要无参构造函数

    }

    public PersonNode(String name, String born) {
        this.name = name;
        this.born = born;
    }

    @Relationship(type = "ACTED_IN", direction = Relationship.OUTGOING)
    public Set<MovieNode> actors;

    public void addActor(MovieNode movie) {
        if (actors == null) {
            actors = new HashSet<>();
        }
        actors.add(movie);
    }

    @Relationship(type = "DIRECTED", direction = Relationship.OUTGOING)
    public Set<MovieNode> directors;

    public void addDirector(MovieNode movie) {
        if (directors == null) {
            directors = new HashSet<>();
        }
        directors.add(movie);
    }

}
