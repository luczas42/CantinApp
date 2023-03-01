package org.apache.maven.cantinappdesktop.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("username")
    String username;
    @SerializedName("name")
    String name;
    @SerializedName("isUser")
    int isUser = 1;
    @SerializedName("email")
    String email;

    public User(String username, String name, int isUser, String email) {
        this.username = username;
        this.name = name;
        this.isUser = isUser;
        this.email = email;
    }

    public User(String username, String name, String email) {
        this.username = username;
        this.name = name;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsUser() {
        return isUser;
    }

    public void setIsUser(int isUser) {
        this.isUser = isUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
