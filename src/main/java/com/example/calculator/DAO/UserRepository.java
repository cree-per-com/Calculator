package com.example.calculator.DAO;

import com.example.calculator.Entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer>{
    boolean existsByUsername(String username);
    UserEntity findByUsername(String username);
}
