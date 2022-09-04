package com.petushkov.webappcollections.services.impl;

import com.petushkov.webappcollections.dto.UserProfileDto;
import com.petushkov.webappcollections.mappers.UserProfileMapper;
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

@Service
@AllArgsConstructor
public class AdminWebServiceImpl implements AdminWebService {

    private UserRepository userRepository;

    private UserProfileMapper userProfileMapper;

    @Override
    public void deleteUsers(List<UserProfileDto> userProfileDtos, HttpSession session, Principal principal) {

        List<User> users = userRepository.findAllById(userProfileDtos.stream().map(u -> u.getId()).collect(Collectors.toList()));

        users = users.stream()
                .map(u -> {
                    u.setStatus("block");
                    if(u.getUsername().equals(principal.getName()))
                        session.invalidate();
                    return u;
                })
                .collect(Collectors.toList());

        userRepository.deleteAll(users);

    }

    @Override
    public List<UserProfileDto> getUsers() {

        return userProfileMapper.entitiesToDtos(userRepository.findAll());

    }

    @Override
    public void blockUsers(List<UserProfileDto> userProfileDtos, HttpSession session, Principal principal) {

        List<User> users = userRepository.findAllById(userProfileDtos.stream().map(u -> u.getId()).collect(Collectors.toList()));

        users = users.stream()
                .map(u -> {
                    u.setStatus("block");
                    if(u.getUsername().equals(principal.getName()))
                        session.invalidate();
                    return u;
                })
                .collect(Collectors.toList());

        userRepository.saveAll(users);
    }

    @Override
    public void unblockUsers(List<UserProfileDto> userProfileDtos) {

        List<User> users = userRepository.findAllById(userProfileDtos.stream().map(u -> u.getId()).collect(Collectors.toList()));

        users = users.stream()
                .map(u -> {
                    u.setStatus("active");
                    return u;
                })
                .collect(Collectors.toList());

        userRepository.saveAll(users);
    }

    @Override
    public void addAdminRole(List<UserProfileDto> userProfileDtos) {

        List<User> users = userRepository.findAllById(userProfileDtos.stream().map(u -> u.getId()).collect(Collectors.toList()));

        users = users.stream()
                .filter(u -> u.getRoles().size() <= 1)
                .map(u-> {
                    u.addRole(new Role(ROLE_ADMIN));
                    return u;
                })
                .collect(Collectors.toList());

        userRepository.saveAll(users);
    }

    @Override
    public void removeAdminRole(List<UserProfileDto> userProfileDtos) {

        List<User> users = userRepository.findAllById(userProfileDtos.stream().map(u -> u.getId()).collect(Collectors.toList()));

        users = users.stream()
                .map(u-> {
                    u.getRoles().removeIf(r-> r.getName() == ROLE_ADMIN);
                    return u;
                })
                .collect(Collectors.toList());

        userRepository.saveAll(users);
    }
}
