package com.tubitak.activitybackend.services.userservice.data.entitity;

import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @com.tubitak.activitybackend.services.userservice.common.validator.TCSecurityNumber
    private String TCSecurityNumber;


    @NotBlank(message = "Name field can't be null")
    private String name;

    @Column
    @NotBlank(message = "Surname field can't be null")
    private String surname;

    @Column(unique = true)
    @NotBlank(message = "Email field can't be null")
    @Email
    private String email;

    @Column
    @NotBlank
    private String userName;

    @Column
    @PastOrPresent
    private LocalDate birthDate;

    @Column
    private String address;

    @ManyToMany(mappedBy = "usersOfActivities")
    private Set<Activity> activitiesOfUser;




    @AssertTrue
    public boolean isValidUserName(){
        return !userName.equals("admin");
    }
    @AssertTrue
    public boolean isValidAddress(){
        return address.length()<250;
    }

}
