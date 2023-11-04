package com.example.BoardICT.auth.security;

public interface AuthToken<T> {
    boolean validate();
    T getClaims();
}
