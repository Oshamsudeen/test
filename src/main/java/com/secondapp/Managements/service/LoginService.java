package com.secondapp.Managements.service;

import com.secondapp.Managements.Encryption.AESEncryptionDecryption;
import com.secondapp.Managements.exception.BadRequestException;
import com.secondapp.Managements.model.entity.User;
import com.secondapp.Managements.model.request.LoginRequest;
import com.secondapp.Managements.model.response.UserResponse;
import com.secondapp.Managements.repository.UserRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSOutput;

@Service
public class LoginService {
    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse loginUser(LoginRequest request) {
        UserResponse response = new UserResponse();
        if (request.getUsername().equals("") || request.getUsername() == null) {
            throw new BadRequestException("Username is mandatory");
        }if (request.getPassword().equals("") || request.getPassword() == null){
            throw new BadRequestException("Password is mandatory");
        }
        //find user by email using fineonebyemail
        User alreadySavedUser = userRepository.findOneByEmail(request.getUsername());
        //if empty throw exception invalid username or password
        if(alreadySavedUser.equals("")) {
            throw new BadRequestException("Username or Password is invalid");
        }

        //decrypt the password
        String decrypted = AESEncryptionDecryption.decrypt(alreadySavedUser.getPassword(),"secrete");

        if(!request.getPassword().equals(decrypted))  {
            //throw exception
            throw new BadRequestException("Access denied");
        }
          response.setEmail(request.getUsername());
          return response;
    }




    }
