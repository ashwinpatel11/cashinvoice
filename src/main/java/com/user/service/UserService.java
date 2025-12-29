package com.user.service;


import com.user.dto.UserRequestDto;
import com.user.dto.UserResponseDto;
import com.user.entity.User;
import java.util.List;

interface UserService {
    UserResponseDto addUser(UserRequestDto requestDto);
    List<User> getAllUser();

    User getSingleUser(long id);
    String deleteUser(long id);


}
