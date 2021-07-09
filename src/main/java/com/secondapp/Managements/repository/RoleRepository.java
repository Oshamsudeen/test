package com.secondapp.Managements.repository;

import com.secondapp.Managements.model.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findOneById(Integer id);
}
