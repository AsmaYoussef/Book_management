package org.horizon.DTO;

import jakarta.validation.constraints.Email;

public class UpdateAuthorRequest {

    private String name;

    @Email
    private String email;

    // getters & setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
