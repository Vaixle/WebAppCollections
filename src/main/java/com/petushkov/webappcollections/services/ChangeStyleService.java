package com.petushkov.webappcollections.services;

import org.springframework.ui.Model;

import java.security.Principal;

public interface ChangeStyleService {

    public void changeStyle(Model model, String style, Principal principal);
}
