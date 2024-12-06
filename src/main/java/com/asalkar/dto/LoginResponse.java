package com.asalkar.dto;

public class LoginResponse {
    private String status;
    private String message;
    private UserDTO user;
    private String token;

    // Constructor
    public LoginResponse(String status, String message,UserDTO userDTO, String token) {
        this.status = status;
        this.message = message;
        this.user = userDTO;
        this.token = token;
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
