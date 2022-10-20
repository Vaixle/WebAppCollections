package com.petushkov.webappcollections.services.impl;

import com.petushkov.webappcollections.models.Role;
import com.petushkov.webappcollections.models.User;
import com.petushkov.webappcollections.repositories.UserRepository;
import com.petushkov.webappcollections.services.AdminWebService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import static com.petushkov.webappcollections.models.ERole.ROLE_ADMIN;


/**
 * Service for admin requests
 */
//@Service
//@AllArgsConstructor
//public class AdminWebServiceImpl implements AdminWebService {
//
//    private UserRepository userRepository;
//
//    private UserProfileMapper userProfileMapper;
//
//    /**
//     * Delete user
//     *
//     * @Param userProfileDtos - list of users
//     * @Param session - current session
//     * @Param principal - user's information
//     */
//    @Override
//    public void deleteUsers(List<UserProfileDto> userProfileDtos, HttpSession session, Principal principal) {
//
//        //find all users by id
//        List<User> users = userRepository.findAllById(userProfileDtos.stream().map(u -> u.getId()).collect(Collectors.toList()));
//
//        //block current session
//        users = users.stream()
//                .map(u -> {
//                    u.setStatus("block");
//                    if(u.getUsername().equals(principal.getName()))
//                        session.invalidate();
//                    return u;
//                })
//                .collect(Collectors.toList());
//
//        //delete users
//        userRepository.deleteAll(users);
//
//    }
//
//    /**
//     * Find all users
//     *
//     * @return list of users dto
//     */
//    @Override
//    public List<UserProfileDto> getUsers() {
//
//        return userProfileMapper.entitiesToDtos(userRepository.findAll());
//
//    }
//
//    /**
//     * Block user
//     *
//     * @Param userProfileDtos - list of users
//     * @Param session - current session
//     * @Param principal - user's information
//     */
//    @Override
//    public void blockUsers(List<UserProfileDto> userProfileDtos, HttpSession session, Principal principal) {
//
//        //find all users by id
//        List<User> users = userRepository.findAllById(userProfileDtos.stream().map(u -> u.getId()).collect(Collectors.toList()));
//
//        //change status to block and block current session
//        users = users.stream()
//                .map(u -> {
//                    u.setStatus("block");
//                    if(u.getUsername().equals(principal.getName()))
//                        session.invalidate();
//                    return u;
//                })
//                .collect(Collectors.toList());
//
//        //save users
//        userRepository.saveAll(users);
//    }
//
//
//    /**
//     * Unblock user
//     *
//     * @Param userProfileDtos - list of users
//     */
//    @Override
//    public void unblockUsers(List<UserProfileDto> userProfileDtos) {
//
//        //find all users by id
//        List<User> users = userRepository.findAllById(userProfileDtos.stream().map(u -> u.getId()).collect(Collectors.toList()));
//
//        //change status to active
//        users = users.stream()
//                .map(u -> {
//                    u.setStatus("active");
//                    return u;
//                })
//                .collect(Collectors.toList());
//
//        //save users
//        userRepository.saveAll(users);
//    }
//
//    /**
//     * Add admin role
//     *
//     * @Param userProfileDtos - list of users
//     */
//    @Override
//    public void addAdminRole(List<UserProfileDto> userProfileDtos) {
//
//        //find all users by id
//        List<User> users = userRepository.findAllById(userProfileDtos.stream().map(u -> u.getId()).collect(Collectors.toList()));
//
//        //add admin role
//        users = users.stream()
//                .filter(u -> u.getRoles().size() <= 1)
//                .map(u-> {
//                    u.addRole(new Role(ROLE_ADMIN));
//                    return u;
//                })
//                .collect(Collectors.toList());
//
//        //save users
//        userRepository.saveAll(users);
//    }
//
//    /**
//     * Remove admin role
//     *
//     * @Param userProfileDtos - list of users
//     */
//    @Override
//    public void removeAdminRole(List<UserProfileDto> userProfileDtos) {
//
//        //find all users by id
//        List<User> users = userRepository.findAllById(userProfileDtos.stream().map(u -> u.getId()).collect(Collectors.toList()));
//
//        //add admin role
//        users = users.stream()
//                .map(u-> {
//                    u.getRoles().removeIf(r-> r.getName() == ROLE_ADMIN);
//                    return u;
//                })
//                .collect(Collectors.toList());
//
//        //save users
//        userRepository.saveAll(users);
//    }
//}
