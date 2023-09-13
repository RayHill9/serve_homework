package com.user.req;

public class UserLogin {
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", position='" + position + '\'' +
                '}';
    }

    private int id;

    private String email;
    private String address;

    private String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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


    public String getEmail() {
        return email;
    }

    public void setEmail(String residence) {
        this.email = residence;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String phone) {
        this.address = phone;
    }


}
