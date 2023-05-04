package com.check24.model;

public class AuthRequest {

    private final String username;
    private final String password;

    public AuthRequest(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static final class Builder {

        private String username;
        private String password;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public AuthRequest build() {
            return new AuthRequest(this);
        }

    }
}
