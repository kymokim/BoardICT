package com.example.BoardICT.comment.dto;

import com.example.BoardICT.article.entity.Article;
import com.example.BoardICT.comment.entity.Comment;
import lombok.Builder;
import lombok.Data;

public class RequestComment {

    @Data
    @Builder
    public static class SaveCommentDto {

        private String commentContents;
        private Long articleId;

        public static Comment toEntity(SaveCommentDto saveCommentDto, Article article, String writer) {
            return Comment.builder()
                    .commentWriter(writer)
                    .commentContents(saveCommentDto.getCommentContents())
                    .article(article)
                    .build();
        }
    }

    @Data
    @Builder
    public static class UpdateCommentDto{
        private Long id;
        private String commentContents;

        public static Comment toEntity(Comment comment, UpdateCommentDto updateCommentDto) {
            comment.update(updateCommentDto.getCommentContents());
            return comment;
        }
    }

}
