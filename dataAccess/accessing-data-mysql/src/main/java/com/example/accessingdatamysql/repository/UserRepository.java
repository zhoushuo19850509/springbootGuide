package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
