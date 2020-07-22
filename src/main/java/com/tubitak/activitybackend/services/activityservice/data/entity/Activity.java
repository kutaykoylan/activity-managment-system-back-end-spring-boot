package com.tubitak.activitybackend.services.activityservice.data.entity;

import com.tubitak.activitybackend.common.data.BaseEntity;
import com.tubitak.activitybackend.services.userservice.common.data.entitity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table (name = "activities")
@SequenceGenerator(name = "idgen", sequenceName = "ACTIVITY_SEQ")
public class Activity extends BaseEntity {

    @Column
    private String title;

    @Column
    private String details;

    @Column
    private double locationLat;

    @Column
    private double locationLng;

    @Column
    @FutureOrPresent
    private LocalDate startDate;

    @Column
    @FutureOrPresent
    private LocalDate endDate;

    @ManyToMany
    @JoinTable(
            name = "users_of_activity",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> usersOfActivities;

    @AssertTrue
    public boolean isValidDate(){
        return endDate.compareTo(startDate)>=0;
    }

    public void setAllData(String title,String details,double locationLat,double locationLng,LocalDate startDate,LocalDate endDate){
        setTitle(title);
        setDetails(details);
        setLocationLat(locationLat);
        setLocationLng(locationLng);
        setStartDate(startDate);
        setEndDate(endDate);
    }
}
