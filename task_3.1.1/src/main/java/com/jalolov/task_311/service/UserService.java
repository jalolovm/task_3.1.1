package com.jalolov.task_311.service;

import com.jalolov.task_311.model.User;
import com.jalolov.task_311.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(long id){
        return userRepository.getOne(id);
    }
    public void save(User user) throws Exception {
       User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            throw new Exception("ПОЛЬЗОВАТЕЛЬ С ТАКИМ ИМЕНЕМ УЖЕ СУЩЕСТВУЕТ");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void update(User user){
        userRepository.save(user);
    }
    public void delete(long id){
        userRepository.deleteById(id);
    }

    public User findByUsername(String name){
        return userRepository.findByUsername(name);
    }
}
