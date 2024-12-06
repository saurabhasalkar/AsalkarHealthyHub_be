package com.asalkar.dto;
public class UserDTO {
    private Long id;
    private String name;
    private String email;

    // Constructor
    public UserDTO(Long long1, String name, String email) {
        this.id = long1;
        this.name = name;
        this.email = email;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
