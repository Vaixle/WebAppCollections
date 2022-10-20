package com.petushkov.webappcollections.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

import java.util.Locale;


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
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
            .addMapping("/**")
            .allowedOrigins("http://localhost:3000")
            .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS")
            .allowedHeaders("Authorization", "Content-Type")
            .allowCredentials(true);
  }

//  @Bean
//  public LocaleResolver localeResolver() {
//    SessionLocaleResolver slr = new SessionLocaleResolver();
//    slr.setDefaultLocale(Locale.US);
//    return slr;
//  }

  @Bean
  public LocaleResolver localeResolver() {
    CookieLocaleResolver slr = new CookieLocaleResolver();
    slr.setDefaultLocale(Locale.US);
    slr.setCookieMaxAge(60 * 60 * 24 * 365 * 10);
    return slr;
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
    lci.setParamName("lang");
    return lci;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    registry.addInterceptor(localeChangeInterceptor());
    registry.addInterceptor(themeChangeInterceptor());
  }


  @Bean
  public ThemeResolver themeResolver() {
    CookieThemeResolver themeResolver = new CookieThemeResolver();
    themeResolver.setDefaultThemeName("light");
    themeResolver.setCookieMaxAge(60 * 60 * 24 * 365 * 10);
    return themeResolver;
  }

  @Bean
  public ThemeChangeInterceptor themeChangeInterceptor() {
    ThemeChangeInterceptor interceptor = new ThemeChangeInterceptor();
    interceptor.setParamName("theme");
    return interceptor;
  }

}
