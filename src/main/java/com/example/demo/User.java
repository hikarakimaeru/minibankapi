package com.example.demo;

public class User {
    private String login;
    private String password;
    private String name;
    private int id;

    public User(String login, String password, String name, int id){
        this.login = login;
        this.password = password;
        this.name = name;
        this.id = id;
    }

    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return password;
    }

    public String getName(){
        return name;
    }
}
