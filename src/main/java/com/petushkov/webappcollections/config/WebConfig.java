package com.petushkov.webappcollections.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Configuration for views
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


  /**
   * Set paths and views for paths
   */
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("main");
    registry.addViewController("/login").setViewName("login");
    registry.addViewController("/login_ru").setViewName("login_ru");
    registry.addViewController("/sign-up_ru").setViewName("registration_ru");
  }


}
