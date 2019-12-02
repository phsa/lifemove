package br.com.lifemove.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String name;
    private String email;
    private String password;
    private List<User> followers;
    private List<User> following;
    private List<SportsEvent> events;

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public User(String name, String username, String email, String password, List<User> followers,  List<User> following, List<SportsEvent> events) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.followers = followers;
        this.following = following;
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<SportsEvent> getEvents() {
        return events;
    }

    public void setEvents(List<SportsEvent> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return (username != null) && username.compareTo(user.getUsername()) == 0;
    }
}
