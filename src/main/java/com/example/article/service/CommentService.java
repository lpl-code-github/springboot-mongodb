package com.example.article.service;

import com.example.article.domain.Comment;
import com.example.article.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lipenglong
 * @version 1.0
 * @describe
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 保存一个评论
     *
     * @param comment
     */
    public void saveComment(Comment comment) {
        // 如果需要自定义主键，可以在这里指定主键，如果不指定，MongoDB会自动生成主键
        commentMapper.save(comment);
    }

    /**
     * 更新评论
     *
     * @param comment
     */
    public void updateComment(Comment comment) {
        commentMapper.save(comment);
    }

    /**
     * 根据id删除评论
     *
     * @param id
     */
    public void deleteComment(String id) {
        commentMapper.deleteById(id);
    }

    /**
     * 查询全部
     *
     * @return
     */
    public List<Comment> findCommentList() {
        return commentMapper.findAll();
    }

    /**
     * 根据id查询评论
     *
     * @param id
     * @return
     */
    public Comment findCommentById(String id) {
        return commentMapper.findById(id).get();
    }

    /**
     * 根据上级评论id和文章id,分页查询
     * @param parentid
     * @param articleid
     * @param page
     * @param size
     * @return
     */
    public Page<Comment> findCommentListPageByParentid(String parentid, String articleid, int page, int size) {
        return commentMapper.findByParentidAndArticleid(parentid, articleid, PageRequest.of(page - 1, size));
    }

    /**
     * 点赞数更新
     * +1
     * @param id
     */
    public void updateCommentLikenum(String id){
        // 查询对象
        Query query = Query.query(Criteria.where("_id").is(id));
        // 更新对象
        Update update = new Update();
        // 局部更新，递增$inc
        update.inc("likenum",1);
        // 参数分别为：查询对象、更新对象、集合的名字或者实体类的类型Comment.class
        mongoTemplate.updateFirst(query,update,"comment");
    }
}
