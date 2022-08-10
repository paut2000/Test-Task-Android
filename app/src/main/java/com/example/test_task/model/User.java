package com.example.test_task.model;

import java.util.ArrayList;

public class User {

    private String id;

    private String username;

    private String email;

    private String roleId;

    private ArrayList<String> permissions;

    public User(String id, String username, String email, String roleId, ArrayList<String> permissions) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roleId = roleId;
        this.permissions = permissions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public ArrayList<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<String> permissions) {
        this.permissions = permissions;
    }
}
