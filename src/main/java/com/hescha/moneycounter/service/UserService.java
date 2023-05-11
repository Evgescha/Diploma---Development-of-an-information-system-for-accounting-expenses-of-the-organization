package com.hescha.moneycounter.service;

import com.hescha.moneycounter.model.Role;
import com.hescha.moneycounter.model.User;
import com.hescha.moneycounter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService extends CrudService<User>  implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public List<User> findByUsernameContains(String username) {
        return repository.findByUsernameContains(username);
    }

    public List<User> findByPassword(String password) {
        return repository.findByPassword(password);
    }

    public List<User> findByPasswordContains(String password) {
        return repository.findByPasswordContains(password);
    }

    public List<User> findByFirstname(String firstname) {
        return repository.findByFirstname(firstname);
    }

    public List<User> findByFirstnameContains(String firstname) {
        return repository.findByFirstnameContains(firstname);
    }

    public List<User> findByLastname(String lastname) {
        return repository.findByLastname(lastname);
    }

    public List<User> findByLastnameContains(String lastname) {
        return repository.findByLastnameContains(lastname);
    }

    public List<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<User> findByEmailContains(String email) {
        return repository.findByEmailContains(email);
    }

    public List<User> findByRolesContains(com.hescha.moneycounter.model.Role roles) {
        return repository.findByRolesContains(roles);
    }

    public List<User> findByExpenseItemsContains(com.hescha.moneycounter.model.ExpenseItem expenseItems) {
        return repository.findByExpenseItemsContains(expenseItems);
    }


    public User update(Long id, User entity) {
        User read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity User not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(User entity, User read) {
        read.setUsername(entity.getUsername());
        read.setPassword(passwordEncoder.encode(entity.getPassword()));
        read.setFirstname(entity.getFirstname());
        read.setLastname(entity.getLastname());
        read.setEmail(entity.getEmail());
        read.setRoles(entity.getRoles());
        read.setImage(entity.getImage());
    }

    public boolean registerNew(User entity) {
        Role read = roleService.read(1);
        entity.getRoles().add(read);
        if (repository.findByUsername(entity.getUsername()) != null) {
            return false;
        }
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        try {
            create(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
