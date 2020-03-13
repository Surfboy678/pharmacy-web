package com.brodacki.janusz.pharmacy.controller;

import com.brodacki.janusz.pharmacy.model.User;
import com.brodacki.janusz.pharmacy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure/rest")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PreAuthorize("hasAnnyRole('ADMIN')")
    @PostMapping("/admin/add")
    public String addedUserByAdmin(@RequestBody User user){
        String pwd = user.getPassword();
        String encryptPwd = bCryptPasswordEncoder.encode(pwd);
        user.setPassword(encryptPwd);
        userRepository.save(user);
        return "user added successful...";
    }
}
