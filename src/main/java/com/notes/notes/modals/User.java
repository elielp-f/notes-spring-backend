package com.notes.notes.modals;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("author")
public class User {
    @Id
    private String id;

    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String hash;

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
