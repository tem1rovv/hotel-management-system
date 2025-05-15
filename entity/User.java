package project2.entity;

import project2.entity.enums.Role;

import java.util.UUID;

public class User {
    private final String id = UUID.randomUUID().toString();
    private String name;
    private String surname;
    private String email;
    private String password;
    private double balance;
    private Role role;


    public User(String name, String surname, String email, String password,Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", role=" + role +
                '}';
    }
}
