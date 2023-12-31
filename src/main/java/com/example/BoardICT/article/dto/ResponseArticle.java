package com.example.BoardICT.article.dto;

import com.example.BoardICT.article.entity.Article;
import com.example.BoardICT.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ResponseArticle {

    @Builder
    @Getter
    public static class GetArticleDto {
        private Long id;
        private LocalDateTime creationDate;
        private LocalDateTime fixedDate;
        private String title;
        private String content;
        private String category;
//        private String imgUrl;
        private String writer;
        private List<CommentListDto> commentList;

        public static GetArticleDto toDto(Article article){
            List<CommentListDto> commentList = new ArrayList<>();
            if (!article.getCommentList().isEmpty())
                article.getCommentList().stream().forEach(comment -> commentList.add(CommentListDto.toDto(comment)));
            return GetArticleDto.builder()
                    .id(article.getId())
                    .creationDate(article.getCreationDate())
                    .fixedDate(article.getFixedDate())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .category(article.getCategory())
//                    .imgUrl(article.getImgUrl())
                    .writer(article.getWriter())
                    .commentList(commentList)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class CommentListDto{
        private Long id;
        private LocalDateTime creationDate;
        private String writer;
        private String content;

        public static CommentListDto toDto(Comment comment) {
            return CommentListDto.builder()
                    .id(comment.getId())
                    .creationDate(comment.getCreationDate())
                    .writer(comment.getCommentWriter())
                    .content(comment.getCommentContents())
                    .build();
        }
    }

    @Builder
    @Getter
    public static class GetAllArticleDto{
        private Long id;
        private LocalDateTime creationDate;
        private String title;
        private String category;
        private String content;
        private String writer;

        public static GetAllArticleDto toDto(Article article){
            return GetAllArticleDto.builder()
                    .id(article.getId())
                    .creationDate(article.getCreationDate())
                    .title(article.getTitle())
                    .category(article.getCategory())
                    .content(article.getContent())
                    .writer(article.getWriter())
                    .build();
        }
    }

    @Builder
    @Getter
    public static class GetByCategoryDto{
        private Long id;
        private LocalDateTime creationDate;
        private String title;
        private String category;
        private String content;
        private String writer;

        public static GetByCategoryDto toDto(Article article){
            return GetByCategoryDto.builder()
                    .id(article.getId())
                    .creationDate(article.getCreationDate())
                    .title(article.getTitle())
                    .category(article.getCategory())
                    .content(article.getContent())
                    .writer(article.getWriter())
                    .build();
        }
    }
}

