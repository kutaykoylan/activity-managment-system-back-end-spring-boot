package com.tubitak.activitybackend.services.userservice.managmentservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import com.tubitak.activitybackend.services.userservice.common.data.entitity.Authority;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Builder
public class UserDTO {

    @com.tubitak.activitybackend.services.userservice.common.validator.TCSecurityNumber
    public final String tcSecurityNumber;


    @NotBlank(message = "Name field can't be null")
    public final String name;

    @NotBlank(message = "Surname field can't be null")
    public final String surname;

    @NotBlank(message = "Email field can't be null")
    @Email
    public final String email;

    @NotBlank
    public final String username;

    public final String password;

    @PastOrPresent
    public final LocalDate birthDate;

    @Column
    public final String address;

    public UserDTO(@JsonProperty("tcSecurityNumber")String TCSecurityNumber, @NotBlank(message = "Name field can't be null")@JsonProperty("name") String name,
                   @NotBlank(message = "Surname field can't be null")@JsonProperty("surname") String surname,
                   @NotBlank(message = "Email field can't be null") @Email@JsonProperty("email") String email,
                   @NotBlank@JsonProperty("username") String username,@JsonProperty("password") String password,
                   @PastOrPresent@JsonProperty("birthDate") LocalDate birthDate, @JsonProperty("address")String address) {
        this.tcSecurityNumber = TCSecurityNumber;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
        this.address = address;
    }

    @AssertTrue
    public boolean isValidAddress(){
        return address.length()<250;
    }
}
