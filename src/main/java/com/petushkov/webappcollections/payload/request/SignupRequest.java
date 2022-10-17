package com.petushkov.webappcollections.payload.request;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignupRequest {


       @NotBlank
       String username;

       @NotBlank
       String password;

       @NotBlank
       String fullName;

       @NotBlank
       @Email
       String email;

}
