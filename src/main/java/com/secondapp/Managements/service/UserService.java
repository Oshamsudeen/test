package com.secondapp.Managements.service;

import com.secondapp.Managements.Encryption.AESEncryptionDecryption;
import com.secondapp.Managements.Encryption.HashingFunctions;
import com.secondapp.Managements.exception.BadRequestException;
import com.secondapp.Managements.exception.NotFoundException;
import com.secondapp.Managements.model.entity.Role;
import com.secondapp.Managements.model.entity.User;
import com.secondapp.Managements.model.request.RoleRequest;
import com.secondapp.Managements.model.request.UserRequest;
import com.secondapp.Managements.model.response.RoleResponse;
import com.secondapp.Managements.model.response.SuccessResponse;
import com.secondapp.Managements.model.response.UserResponse;
import com.secondapp.Managements.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//
//    public UserResponse createUser(UserRequest request) {
//        UserResponse response = new UserResponse();
//        if (request.getEmail().equals("") || request.getEmail() == null) {
//            throw new BadRequestException("Email is mandatory");
//        }
//        User alreadySavedUser = userRepository.findOneByEmail(request.getEmail());
//        if (alreadySavedUser != null) {
//            throw new BadRequestException("Email already exist");
////
//        }


        public SuccessResponse createUser (UserRequest request){
            if (request.getEmail().equals("") || request.getEmail() == null) {
                throw new BadRequestException("Email is required");
            }
            User alreadySavedUser = userRepository.findOneByEmail((request.getEmail()));
            if (alreadySavedUser != null) {
            throw new BadRequestException("Email already exist");
       }
            User user = new User();
            user.setFirstName(request.getFirstName());
            user.setSecondName(request.getSecondName());
            user.setUsername(request.getUsername());
            user.setPassword(request.getPassword());
            user.setEmail(request.getEmail());
            user.setPassword(HashingFunctions.getHash(request.getPassword().getBytes(), "SHA-512"));
            User savedUser = userRepository.save(user);
            return buildSuccessResponse(createUserResponse(savedUser));
        }
public SuccessResponse UpdateUser(String email, UserRequest request) {
    User alreadySavedUser = fetchUserByEmail(email);
    User SavedUser = userRepository.findOneByEmail(email);
    if (alreadySavedUser == null) {
        throw new BadRequestException("User not found");
    }
    alreadySavedUser.setFirstName(request.getFirstName());
    alreadySavedUser.setSecondName(request.getSecondName());
    alreadySavedUser.setPassword(request.getPassword());
    return buildSuccessResponse(createUserResponse(SavedUser));
}

    private Map<String, Object> createUserResponse(User savedUser) {
        Map<String, Object> data = new HashMap<>();
        data.put("user", UserResponse.fromUser(savedUser));
        return data;
    }

    private User fetchUserByEmail(String email) {
        User alreadySavedUser = userRepository.findOneByEmail(email);
        if(alreadySavedUser == null) {
            throw new NotFoundException("Email with this id does not exist");
        }
        return alreadySavedUser;
    }

    private SuccessResponse buildSuccessResponse(Map<String, Object> data) {
        return SuccessResponse.builder().status(true).data(data).build();
    }
}

