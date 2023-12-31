package com.example.BoardICT.auth.dto;

import com.example.BoardICT.auth.domain.Auth;
import lombok.Builder;
import lombok.Data;

public class RequestAuth {

//    @Builder
//    @Data
//    public static class oAuthDto{
//        private String email;
//        private String username;
//
//        public static Auth toEntity(String name, String email){
//            return Auth.builder()
//                    .email(email)
//                    .username(name)
//                    .build();
//        }
//    }

    @Builder
    @Data
    public static class RegisterUserDto{
        private String email;
        private String password;
        private String username;
        private Long studentId;
        private String major;

        public static Auth toEntity(RegisterUserDto registerUserDto, String salt, String encryptedPassword){
            return Auth.builder()
                    .email(registerUserDto.getEmail())
                    .password(encryptedPassword)
                    .username(registerUserDto.getUsername())
                    .studentId(registerUserDto.getStudentId())
                    .major(registerUserDto.getMajor())
                    .salt(salt)
                    .build();
        }
    }

    @Builder
    @Data
    public static class LoginUserRqDto{
        private String email;
        private String password;
    }

    @Builder
    @Data
    public static class UpdateUserDto{
        private String password;
        private String username;

        public static Auth toEntity(Auth user, UpdateUserDto updateUserDto, String salt, String encryptedPassword){
            user.update(encryptedPassword, updateUserDto.getUsername(), salt);
            return user;
        }
    }
}
