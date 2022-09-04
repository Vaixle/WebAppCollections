package com.petushkov.webappcollections.services.impl;

import com.petushkov.webappcollections.models.User;
import com.petushkov.webappcollections.repositories.UserRepository;
import com.petushkov.webappcollections.services.ChangeLanguageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;

@Service
@AllArgsConstructor
public class ChangeLanguageServiceImpl implements ChangeLanguageService {

    private UserRepository userRepository;

    @Override
    public String changeLanguage(Model model, String lang, Principal principal) {

        if(lang != null && model.getAttribute("lang") != lang) {
            if (principal != null) {
                User user = userRepository.findByUsername(principal.getName()).get();
                String userLang = user.getLanguage();

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

        if(principal != null) {
            User user = userRepository.findByUsername(principal.getName()).get();
            String userLang = user.getLanguage();
            model.addAttribute("lang", userLang);
        }

        return  model.getAttribute("lang") != null  ?
                model.getAttribute("lang").toString() : lang;

    }
}
