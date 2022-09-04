package com.petushkov.webappcollections.services;

import org.springframework.ui.Model;

import java.security.Principal;

public interface ChangeLanguageService {

    public String changeLanguage(Model model, String lang, Principal principal);
}
