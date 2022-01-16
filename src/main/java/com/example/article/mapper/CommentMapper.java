package com.example.article.mapper;

import com.example.article.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * @author lipenglong
 * @version 1.0
 * @describe
 */
public interface CommentMapper extends MongoRepository<Comment,String> {
    /**
     * 根据上级评论id和文章id,分页查询
     * @param parentid
     * @param articleid
     * @param pageable
     * @return
     */
    Page<Comment> findByParentidAndArticleid(String parentid,String articleid,Pageable pageable);
}
