package com.bracu.rsmr.Employee;

import com.bracu.rsmr.User.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eid;
    private String username;
    private String password;
    private List<String> roles;

    @ManyToMany
    @JoinTable(
            name = "employee_user",
            joinColumns = @JoinColumn(name = "eid"),
            inverseJoinColumns = @JoinColumn(name = "users")
    )
    private List<User> users;

    public Employee() {

    }

    public Employee(String username, String password, List<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = new ArrayList<>(roles);
    }

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles = new ArrayList<>(Arrays.asList("Employee", "Manager"));

    }




    public Long getId() {
        return eid;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
