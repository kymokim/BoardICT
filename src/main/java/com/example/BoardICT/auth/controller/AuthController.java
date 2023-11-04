package com.example.BoardICT.auth.controller;

import com.example.BoardICT.auth.dto.RequestAuth;
import com.example.BoardICT.auth.dto.ResponseAuth;
import com.example.BoardICT.auth.security.JwtAuthTokenProvider;
import com.example.BoardICT.auth.service.AuthService;
import com.example.BoardICT.common.dto.ResponseMessage;
import com.example.BoardICT.common.exception.error.LoginFailedException;
import com.example.BoardICT.common.exception.error.RegisterFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
//@PropertySource("classpath:/secret/application-oauth.properties")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

//    @GetMapping("/oAuth")
//    public String oAuth(OAuth2AuthenticationToken authentication) {
//        System.out.println("contoller accessed.");
//        String accessToken = "";
//        if (authentication != null && authentication.isAuthenticated()) {
//            OAuth2User oauth2User = authentication.getPrincipal();
//            String name = oauth2User.getAttribute("name");
//            String email = oauth2User.getAttribute("email");
//            accessToken = authService.oAuth(name, email);
//        }
//        else
//            throw new RegisterFailedException();
////        ResponseMessage responseMessage = ResponseMessage.builder()
////                .message("oAuth authorized successfully.")
////                .data(accessToken)
////                .build();
//        System.out.println(accessToken);
//        return accessToken;
//    }

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody RequestAuth.RegisterUserDto registerUserDto) {
        authService.registerUser(registerUserDto);
//        ResponseMessage responseMessage = ResponseMessage.builder()
//                .message("User registered successfully.")
//                .build();
        return "User registered successfully.";
    }

    @PostMapping("/login")
    public ResponseAuth.LoginUserRsDto loginUser(@Valid @RequestBody RequestAuth.LoginUserRqDto loginUserRqDto) {
        ResponseAuth.LoginUserRsDto response = authService.loginUser(loginUserRqDto).orElseThrow(()->new LoginFailedException());
//        ResponseMessage responseMessage = ResponseMessage.builder()
//                .message("User logged in successfully.")
//                .data(response)
//                .build();
        return response;
    }

    @PutMapping("/update")
    public String updateUser(HttpServletRequest request, @Valid @RequestBody RequestAuth.UpdateUserDto updateUserDto) {
        Optional<String> token = jwtAuthTokenProvider.getAuthToken(request);
        authService.updateUser(token, updateUserDto);
//        ResponseMessage responseMessage = ResponseMessage.builder()
//                .message("User information updated successfully.")
//                .build();
        return "User information updated successfully.";
    }

    @GetMapping("/get")
    public ResponseAuth.GetUserDto getUser(HttpServletRequest request){
        Optional<String> token = jwtAuthTokenProvider.getAuthToken(request);
        ResponseAuth.GetUserDto response = authService.getUser(token);
//        ResponseMessage responseMessage = ResponseMessage.builder()
//                .message("User information retrieved successfully.")
//                .data(response)
//                .build();
        return response;
    }

}
