package org.leftbrained.roombinding.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String login;
    public int loginCount;

    public User(String name, String login, int loginCount) {
        this.name = name;
        this.login = login;
        this.loginCount = loginCount;
    }
    public User(String name, String login) {
        this.name = name;
        this.login = login;
        this.loginCount = 0;
    }
    public User() {
        this.name = "No name";
        this.login = "No email";
        this.loginCount = 0;
    }
}
