package com.example.demo.user;

import com.example.demo.account.Account;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToMany(mappedBy = "user")
    private List<Account> accList = new ArrayList<>();

    public User(String login, String password, String name){
        this.login = login;
        this.password = password;
        this.name = name;
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
