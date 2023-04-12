package com.hescha.moneycounter.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "MyUser")
public class User extends AbstractEntity {
    private String username;
    private String password;
    private String email;
    @ManyToOne
    private Role role;
}
