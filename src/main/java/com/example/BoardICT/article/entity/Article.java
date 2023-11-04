package com.example.BoardICT.article.entity;

import com.example.BoardICT.comment.entity.Comment;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "article")
@Entity
@Getter
@NoArgsConstructor
@Data
public class Article {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "fix_date")
    private LocalDateTime fixedDate = LocalDateTime.now();

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "title")
    private String title;

    @Column(name = "writer")
    private String writer;
    @Column(name = "content")
    private String content;

    @Column(name = "category")
    private String category;

//    @Column(name = "img_url")
//    private String imgUrl;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

//    private String imgUrl;

    @Builder
    public Article(String title, String content, String writer, String category){ // 빌더 사용에 필요
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.category = category;
    }

    public void update(String title, String content, String category){
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void addComments(Comment comment){
        this.commentList.add(comment);
    }
}
