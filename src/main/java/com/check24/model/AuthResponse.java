package com.check24.model;

public class AuthResponse {

    private final String jwtToken;


    public AuthResponse(Builder builder) {
        this.jwtToken = builder.jwtToken;
    }

    public String getJwtToken() {
        return this.jwtToken;
    }

    public static final class Builder {

        private String jwtToken;

        public Builder setJwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        public AuthResponse build() {
            return new AuthResponse(this);
        }
    }
}
