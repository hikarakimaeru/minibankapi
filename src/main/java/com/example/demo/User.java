package com.example.demo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Column(name = "login")
    private String login;
    @Column(name = "password_hash")
    private String password;
    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "user")
    private List<Account> accList = new ArrayList<>();

    public User(String login, String password, String name, int id){
        this.login = login;
        this.password = password;
        this.name = name;
        this.id = id;
    }

    protected User(){
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
