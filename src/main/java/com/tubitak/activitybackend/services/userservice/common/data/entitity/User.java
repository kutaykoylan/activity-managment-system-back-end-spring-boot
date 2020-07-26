package com.tubitak.activitybackend.services.userservice.common.data.entitity;

import com.tubitak.activitybackend.common.data.BaseEntity;
import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.UserDetails;

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
@SequenceGenerator(name = "idgen", sequenceName = "USER_SEQ")
public class User implements UserDetails, CredentialsContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true,updatable = false,nullable = true)
    @com.tubitak.activitybackend.services.userservice.common.validator.TCSecurityNumber
    private String tcSecurityNumber;


    @NotBlank(message = "Name field can't be null")
    @Column
    private String name;

    @Column
    @NotBlank(message = "Surname field can't be null")
    private String surname;

    @Column(unique = true,updatable = false,nullable = true)
    @NotBlank(message = "Email field can't be null")
    @Email
    private String email;

    @Column(unique = true,updatable = false)
    @NotBlank
    private String username;

    @Column
    private String password;

    @Column
    @PastOrPresent
    private LocalDate birthDate;

    @Column
    private String address;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_AUTHORITIES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHORITRY_ID"))
    private Set<Authority> authorities;
    
    @Override
    public void eraseCredentials() {
        password= null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
