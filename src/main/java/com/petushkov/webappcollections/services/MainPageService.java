package com.petushkov.webappcollections.services;

import org.springframework.ui.Model;

import java.security.Principal;

public interface MainPageService {

    String getMainPage(Model model, Principal principal);
}
