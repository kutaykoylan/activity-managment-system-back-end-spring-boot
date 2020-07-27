package com.tubitak.activitybackend.services.usersactivityservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@Builder
public class UserInformationDTO {

    public final String tcSecurityNumber;

    public final String name;

    public final String surname;

    public final String email;

    public final String username;

    public final LocalDate birthDate;

    public final String address;

    public UserInformationDTO(@JsonProperty("tcSecurityNumber")String TCSecurityNumber, @JsonProperty("name") String name,
                              @JsonProperty("surname") String surname,
                              @JsonProperty("email") String email,
                              @JsonProperty("username") String username,
                              @JsonProperty("birthDate") LocalDate birthDate, @JsonProperty("address")String address) {
        this.tcSecurityNumber = TCSecurityNumber;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.birthDate = birthDate;
        this.address = address;
    }
}
