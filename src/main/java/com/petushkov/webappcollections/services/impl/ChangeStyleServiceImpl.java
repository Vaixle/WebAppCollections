package com.petushkov.webappcollections.services.impl;

import com.petushkov.webappcollections.models.User;
import com.petushkov.webappcollections.repositories.UserRepository;
import com.petushkov.webappcollections.services.ChangeStyleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;

/**
 * Service with functions to processing change style
 */
@Service
@AllArgsConstructor
public class ChangeStyleServiceImpl implements ChangeStyleService {

    private UserRepository userRepository;

    /**
     * @param model
     * @param style - value for style
     * @param principal - user details
     * @return language selected
     */
    @Override
    public void changeStyle(Model model, String style, Principal principal) {

        // If the style is selected and there are no changes, skip the block
        if(style != null && model.getAttribute("style") != style) {

            //  If the user is authorized, check the value saved by the user
            if (principal != null) {
                User user = userRepository.findByUsername(principal.getName()).get();
                String userStyle = user.getStyle();

                //  If the user has a saved value of the style and no changes, take it else add changes
                if(userStyle != null && userStyle == style) {
                    style = userStyle;
                    model.addAttribute("style", style);
                } else if(style != null) {
                    user.setStyle(style);
                    userRepository.save(user);
                }
            }

            model.addAttribute("style", style);

        }

        //take selected style
        if(principal != null) {
            User user = userRepository.findByUsername(principal.getName()).get();
            String userStyle = user.getStyle();
            model.addAttribute("style", userStyle);
        }


    }

}
