package com.petushkov.webappcollections.services.impl;

import com.petushkov.webappcollections.models.User;
import com.petushkov.webappcollections.repositories.UserRepository;
import com.petushkov.webappcollections.services.ChangeLanguageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;


/**
 * Service with functions to processing change language
 */
@Service
@AllArgsConstructor
public class ChangeLanguageServiceImpl implements ChangeLanguageService {

    private UserRepository userRepository;

    /**
     * @param model
     * @param lang - value for language
     * @param principal - user details
     * @return language selected
     */
    @Override
    public String changeLanguage(Model model, String lang, Principal principal) {

        // If the language is selected and there are no changes, skip the block
        if(lang != null && model.getAttribute("lang") != lang) {

            //  If the user is authorized, check the value saved by the user
            if (principal != null) {
                User user = userRepository.findByUsername(principal.getName()).get();
                String userLang = user.getLanguage();

                //  If the user has a saved value of the language and no changes, take it else add changes
                if(userLang != null && userLang == lang) {
                    lang = userLang;
                    model.addAttribute("lang", lang);
                } else if(lang != null) {
                    user.setLanguage(lang);
                    userRepository.save(user);
                }
            }

            model.addAttribute("lang", lang);

        }

        //take selected language
        if(principal != null) {
            User user = userRepository.findByUsername(principal.getName()).get();
            String userLang = user.getLanguage();
            model.addAttribute("lang", userLang);
        }

        //if language is null take eng page default
        return  model.getAttribute("lang") != null  ?
                model.getAttribute("lang").toString() : lang;

    }
}
