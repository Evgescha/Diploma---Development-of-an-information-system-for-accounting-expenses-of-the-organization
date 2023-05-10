package com.hescha.moneycounter.service;

import com.hescha.moneycounter.model.Role;
import com.hescha.moneycounter.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleService extends CrudService<Role> {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<Role> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Role> findByNameContains(String name) {
        return repository.findByNameContains(name);
    }


    public Role update(Long id, Role entity) {
        Role read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity Role not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(Role entity, Role read) {
        read.setName(entity.getName());
    }
}
