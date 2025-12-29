package com.user.service;

import com.user.dto.UserRequestDto;
import com.user.dto.UserResponseDto;
import com.user.entity.User;
import com.user.enums.Message;
import com.user.enums.Role;
import com.user.exception.CustomException;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        if(userRepository.existsByEmailOrMobile(userRequestDto.email(),userRequestDto.mobile())){
            throw new CustomException(Message.Email_Or_Mobile_Already_Exist.name());
        }
        User user = User.dtoToUser(userRequestDto);
        user.setRole(Role.CUSTOMER.name());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User user1= userRepository.save(user);
        UserResponseDto responseDto=new UserResponseDto(user1.getId(), user1.getName(),user1.getEmail(),user1.getMobile());
        return responseDto;
    }
    public User getSingleUser(long id) {
        return userRepository.findById(id).orElseThrow(() -> new CustomException(Message.User_Not_Found.name()));
    }

    @Override
    public String deleteUser(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException(Message.User_Not_Found.name()));
        userRepository.deleteById(id);
        return Message.User_Deleted_Successfully.name();
    }

    public List<User> getAllUser() {
        List<User> userList = userRepository.findAll();
        if(userList.isEmpty()){
            throw new CustomException(Message.User_Not_Found.name());
        }
        return userList;
    }







}
