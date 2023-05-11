package com.hescha.moneycounter.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "MyUser")
public class User extends AbstractEntity {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String image;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    @OneToMany
    private List<ExpenseItem> expenseItems = new ArrayList<>();

    public String getFullname() {
        return firstname + " " + lastname;
    }
    public String getRole(){
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.joining(", "));
    }
}
