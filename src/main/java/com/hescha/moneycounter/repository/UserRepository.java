package com.hescha.moneycounter.repository;

import com.hescha.moneycounter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findByUsernameContains(String username);

    List<User> findByPassword(String password);

    List<User> findByPasswordContains(String password);

    List<User> findByFirstname(String firstname);

    List<User> findByFirstnameContains(String firstname);

    List<User> findByLastname(String lastname);

    List<User> findByLastnameContains(String lastname);

    List<User> findByEmail(String email);

    List<User> findByEmailContains(String email);

    List<User> findByRolesContains(com.hescha.moneycounter.model.Role roles);

    List<User> findByExpenseItemsContains(com.hescha.moneycounter.model.ExpenseItem expenseItems);
}
