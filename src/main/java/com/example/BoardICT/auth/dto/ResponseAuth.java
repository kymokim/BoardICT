package com.example.BoardICT.auth.dto;

import com.example.BoardICT.auth.domain.Auth;
import lombok.Builder;
import lombok.Data;

public class ResponseAuth {
    @Data
    @Builder
    public static class LoginUserRsDto{
        private String accessToken;

        public static LoginUserRsDto toDto(String accessToken){
            return LoginUserRsDto.builder()
                    .accessToken(accessToken)
                    .build();
        }
    }

    @Data
    @Builder
    public static class GetUserDto{
        private String email;
        private String username;
        private Long studentId;
        private String major;

        public static GetUserDto toDto(Auth user){
            return GetUserDto.builder()
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .studentId(user.getStudentId())
                    .major(user.getMajor())
                    .build();
        }
    }
}
