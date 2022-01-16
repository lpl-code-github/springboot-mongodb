package com.example.article.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author lipenglong
 * @version 1.0
 * @describe 文章评论实体类
 */
// 把一个java类声明为mongodb的文档，可以通过collection参数指定这个类对应的文档。
// @Document(collection="mongodb 对应 collection 名")
@Document(collection = "comment") // 可以省略，如果省略，则默认使用类名小写映射集合
// 复合索引
// @CompoundIndex( def = "{'userid': 1, 'nickname': -1}")
@Data
@Accessors(chain = true)
public class Comment implements Serializable {
    // 主键标识，该属性的值会自动对应mongodb的主键字段"_id"，如果该属性名就叫“id”,则该注解可以省略，否则必须写
    @Id
    private String id;//主键
    // //该属性对应mongodb的字段的名字，如果一致，则无需该注解
    @Field("content")
    private String content; // 评论内容
    // 添加了一个单字段的索引
    @Indexed
    private String userid; // 发布人ID
    private String nickname; // 昵称
    private LocalDateTime createdatetime; // 评论的日期时间
    private Integer likenum; // 点赞数
    private Integer replynum; // 回复数
    private String state; // 状态
    private String parentid; // 上级ID
    private String articleid;
}
