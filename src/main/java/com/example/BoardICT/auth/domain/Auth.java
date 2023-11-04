package com.example.BoardICT.auth.domain;

import lombok.*;

import javax.persistence.*;

@Table(name="auth")
@Entity
@Getter
@NoArgsConstructor
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="username")
    private String username;

    @Column(name="studentId")
    private Long studentId;

    @Column(name="major")
    private String major;

    @Column(name="salt")
    private String salt;

    @Builder
    public Auth(String email, String password, String username, Long studentId, String major, String salt){
        this.email = email;
        this.password = password;
        this.username = username;
        this.studentId = studentId;
        this.major = major;
        this.salt = salt;

    }

    public void update(String password, String username, String salt) {
        this.password = password;
        this.username = username;
        this.salt = salt;
    }
}
