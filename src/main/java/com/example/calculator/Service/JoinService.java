package com.example.calculator.Service;

import com.example.calculator.DAO.JoinDTO;
import com.example.calculator.Entity.UserEntity;
import com.example.calculator.DAO.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

    public void joinProc(JoinDTO joinDTO) {
        boolean isDuplicate = userRepository.existsByUsername(joinDTO.getUsername());
        if(isDuplicate) return;

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(joinDTO.getUsername());
        userEntity.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        userEntity.setRole("ROLE_USER");

        userRepository.save(userEntity);
    }
}
