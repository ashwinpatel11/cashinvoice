package com.user.jwt;

import com.user.entity.User;
import com.user.enums.Message;
import com.user.exception.CustomException;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

    @Autowired
    private UserRepository userRepository;

    public User getLoggedInUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String email = null;
            if (principal instanceof UserDetails) {
                email = ((UserDetails) principal).getUsername();
            } else {
                email = principal.toString();
            }
            User user = userRepository.findByEmail(email);
            return user;

        }else {
            throw new CustomException(Message.User_Not_Found.name());
        }
    }
}
