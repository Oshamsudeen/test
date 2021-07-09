package com.secondapp.Managements.repository;

import com.secondapp.Managements.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
   User findOneByEmail(String email);
}
