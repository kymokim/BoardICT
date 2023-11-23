package com.example.BoardICT.article.service;

import com.example.BoardICT.article.dto.RequestArticle;
import com.example.BoardICT.article.dto.ResponseArticle;
import com.example.BoardICT.article.entity.Article;
import com.example.BoardICT.article.repository.ArticleRepository;
import com.example.BoardICT.auth.repository.AuthRepository;
import com.example.BoardICT.auth.security.JwtAuthToken;
import com.example.BoardICT.auth.security.JwtAuthTokenProvider;
//import com.example.BoardICT.common.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService { // 오류 던지긴 하는데 안 날라옴

    private final ArticleRepository articleRepository;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;
    private final AuthRepository authRepository;
//    private final S3Service s3Service;

    public void createArticle(RequestArticle.CreateArticleDto createArticleDto, Optional<String> token){

        String email = null;
        if (token.isPresent()) {
            JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(token.get());
            email = jwtAuthToken.getClaims().getSubject();
        }
        String writer = authRepository.findByEmail(email).getUsername();
        Article article = RequestArticle.CreateArticleDto.toEntity(createArticleDto, writer);
        articleRepository.save(article);
    }

    public List<ResponseArticle.GetAllArticleDto> getAllArticle(){

        List<Article> articles = articleRepository.findAllByOrderByFixedDateDesc();
        List<ResponseArticle.GetAllArticleDto> list = new ArrayList<>();
        articles.stream().forEach(article -> list.add(ResponseArticle.GetAllArticleDto.toDto(article)));
        return list;
    }

    public ResponseArticle.GetArticleDto getArticle(Long id){

        Article article = articleRepository.findById(id).get();
        return ResponseArticle.GetArticleDto.toDto(article);
    }

    public List<ResponseArticle.GetByCategoryDto> getByCategory(String category){
        List<Article> articles = articleRepository.findAllByCategory(category);
        List<ResponseArticle.GetByCategoryDto> list = new ArrayList<>();
        articles.stream().forEach(article -> list.add(ResponseArticle.GetByCategoryDto.toDto(article)));
        return list;
    }

    public void updateArticle(RequestArticle.UpdateArticleDto updateArticleDto, Optional<String> token) {

        String email = null;
        if (token.isPresent()) {
            JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(token.get());
            email = jwtAuthToken.getClaims().getSubject();
        }
        String writer = authRepository.findByEmail(email).getUsername();
        Article originalArticle = articleRepository.findById(updateArticleDto.getId()).get();
        if (originalArticle.getWriter().equals(writer)) {
            Article updatedArticle = RequestArticle.UpdateArticleDto.toEntity(originalArticle, updateArticleDto);
            articleRepository.save(updatedArticle);
        }
        else
            throw new RuntimeException();
//        if(originalArticle == null) { throw new NotFoundTaskException(); }
    }


    public void deleteArticle(Long id,Optional<String> token) {

        String email = null;
        if (token.isPresent()) {
            JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(token.get());
            email = jwtAuthToken.getClaims().getSubject();
        }
        String writer = authRepository.findByEmail(email).getUsername();
        Article article = articleRepository.findById(id).get();
        if (article.getWriter().equals(writer)) {

            articleRepository.delete(article);
        }
        else
            throw new RuntimeException();

    }

//    public String uploadImg(MultipartFile file, long articleId){
//        Article article = articleRepository.findById(articleId).get();

//        if (!task.getImgUrl().isEmpty())
//            s3Service.deleteFile(task.getImgUrl());

//        String url = "";
//        try {
//            url = s3Service.upload(file,"article");
//        }
//        catch (IOException e){
//            System.out.println("S3 upload failed.");
//        }

//        article.setImgUrl(url);
//        articleRepository.save(article);
//        return url;
//    }
}


