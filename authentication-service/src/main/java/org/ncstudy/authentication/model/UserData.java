package org.ncstudy.authentication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "app_user")
public class UserData {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "active")
    private boolean active;

    @Column(name = "activation_code")
    @JsonIgnore
    private UUID activationCode;

    @Column(name = "reset_code")
    @JsonIgnore
    private UUID resetPasswordCode;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "app_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    public UserData() {}

    public UserData(String username, String password, List<Role> roles, boolean active, String email) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.active = active;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(UUID activationCode) {
        this.activationCode = activationCode;
    }

    public UUID getResetPasswordCode() {
        return resetPasswordCode;
    }

    public void setResetPasswordCode(UUID resetPasswordCode) {
        this.resetPasswordCode = resetPasswordCode;
    }
}
