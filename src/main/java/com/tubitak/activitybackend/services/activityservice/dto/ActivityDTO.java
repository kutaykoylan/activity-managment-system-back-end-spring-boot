package com.tubitak.activitybackend.services.activityservice.dto;
import com.sun.istack.Nullable;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActivityDTO {

    private Long id;

    private String title;

    private  String details;

    private double locationLat;

    private double locationLng;

    @FutureOrPresent
    private LocalDate startDate;

    @FutureOrPresent
    private LocalDate endDate;


    @AssertTrue
    public boolean isValidDate(){
        return endDate.compareTo(startDate)>=0;
    }
}