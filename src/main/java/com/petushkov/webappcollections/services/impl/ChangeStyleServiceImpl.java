package com.petushkov.webappcollections.services.impl;

import com.petushkov.webappcollections.models.User;
import com.petushkov.webappcollections.repositories.UserRepository;
import com.petushkov.webappcollections.services.ChangeStyleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;

@Service
@AllArgsConstructor
public class ChangeStyleServiceImpl implements ChangeStyleService {

    private UserRepository userRepository;

    @Override
    public void changeStyle(Model model, String style, Principal principal) {

        if(style != null && model.getAttribute("style") != style) {
            if (principal != null) {
                User user = userRepository.findByUsername(principal.getName()).get();
                String userStyle = user.getStyle();

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

        if(principal != null) {
            User user = userRepository.findByUsername(principal.getName()).get();
            String userStyle = user.getStyle();
            model.addAttribute("style", userStyle);
        }

//        return  model.getAttribute("lang") != null  ?
//                model.getAttribute("lang").toString() : style;

    }

}
