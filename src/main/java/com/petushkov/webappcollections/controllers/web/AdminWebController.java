package com.petushkov.webappcollections.controllers.web;

import com.petushkov.webappcollections.dto.UserProfileDto;
import com.petushkov.webappcollections.mappers.UserProfileMapper;
import com.petushkov.webappcollections.models.ERole;
import com.petushkov.webappcollections.models.Role;
import com.petushkov.webappcollections.models.User;
import com.petushkov.webappcollections.repositories.UserRepository;
import com.petushkov.webappcollections.services.impl.AdminWebServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import static com.petushkov.webappcollections.models.ERole.ROLE_ADMIN;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminWebController {

    private AdminWebServiceImpl adminWebService;


    @GetMapping
    @ApiOperation(value = "Admin page", notes = "Get admin page")
    String getAdminPage(Model model) {

        model.addAttribute("users", adminWebService.getUsers());

        return "admin";
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete user", notes = "Delete list of users")
    public void deleteUsers(@RequestBody List<UserProfileDto> userProfileDtos, HttpSession session, Principal principal) {
        adminWebService.deleteUsers(userProfileDtos, session, principal);
    }

    @PutMapping("/block")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Block user", notes = "Block list of users")
    public void blockUsers(@RequestBody List<UserProfileDto> userProfileDtos, HttpSession session, Principal principal) {
        adminWebService.blockUsers(userProfileDtos, session, principal);
    }


    @PutMapping("/unblock")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Unblock user", notes = "Unlock list of users")
    public void unblockUsers(@RequestBody List<UserProfileDto> userProfileDtos) {
        adminWebService.unblockUsers(userProfileDtos);
    }

    @PutMapping("/add-admin-role")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add admin role", notes = "Add admin role for list of users")
    public void addAdminRole(@RequestBody List<UserProfileDto> userProfileDtos) {
        adminWebService.addAdminRole(userProfileDtos);
    }

    @PutMapping("/remove-admin-role")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Remove admin role", notes = "Remove admin role for list of users")
    public void removeAdminRole(@RequestBody List<UserProfileDto> userProfileDtos) {
        adminWebService.removeAdminRole(userProfileDtos);
    }

}
