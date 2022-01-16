package com.example.article;

import com.example.article.domain.Comment;
import com.example.article.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Slf4j
class ArticleApplicationTests {
    @Autowired
    private CommentService commentService;

    /**
     * 保存一个评论
     */
    @Test
    void testSaveComment() {
        Comment comment = new Comment();
        comment.setArticleid("100000")
                .setContent("comment")
                .setCreatedatetime(LocalDateTime.now())
                .setUserid("1011")
                .setNickname("xiaoming")
                .setState("1")
                .setLikenum(0)
                .setReplynum(0)
                .setParentid("3");

        commentService.saveComment(comment);
    }

    /**
     * 查询所有数据
     */
    @Test
    void testFindAll() {
        List<Comment> commentList = commentService.findCommentList();
        log.info(String.valueOf(commentList));
    }

    /**
     * 根据id查询
     */
    @Test
    void testFindById() {
        Comment commentById = commentService.findCommentById("61e3f0ca56b56e39251f3ea8");
        log.info(String.valueOf(commentById));
    }

    /**
     * 更新
     */
    @Test
    void testUpdateComment() {
        Comment comment = new Comment();
        comment.setId("61e3f0ca56b56e39251f3ea8")
                .setArticleid("100000")
                .setContent("修改后的数据")
                .setCreatedatetime(LocalDateTime.now())
                .setUserid("1009")
                .setNickname("万物皆可瞄")
                .setState("1")
                .setLikenum(0)
                .setReplynum(0);
        commentService.updateComment(comment);
        testFindById();
    }

    /**
     * 根据id删除
     */
    @Test
    void testDeleteComment() {
        commentService.deleteComment("61e3f0ca56b56e39251f3ea8");
    }

    /**
     * 分页查询
     */
    @Test
    void testFindCommentListByParentid() {
        Page<Comment> pagelist = commentService.findCommentListPageByParentid("3", "100000", 1, 2);
        log.info("总记录行数：{}", pagelist.getTotalElements());
        log.info("总页数：{}", pagelist.getTotalPages());
        log.info("当前页数：{}", pagelist.getNumber() + 1);
        log.info("当前页显示条数：{}", pagelist.getSize());
        log.info("当前页数据：{}", pagelist.getContent());
    }

    /**
     * 点赞+1
     */
    @Test
    void testUpdateCommentLikenum() {
        String id = "61e3f55791873208b036358a";
        // 查询原来的数据
        Comment comment = commentService.findCommentById(id);
        log.info("点赞前的likenum：{}", comment.getLikenum());
        // 点赞后
        commentService.updateCommentLikenum(id);
        // 查询原来的数据
        Comment updateComment = commentService.findCommentById(id);
        log.info("点赞后的likenum：{}", updateComment.getLikenum());
    }
}
