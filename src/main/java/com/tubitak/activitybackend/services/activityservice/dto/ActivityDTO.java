package com.tubitak.activitybackend.services.activityservice.dto;
import com.sun.istack.Nullable;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActivityDTO {

    @NotBlank
    private Long id;

    @NotBlank(message = "Title can't be blank")
    private String title;

    @NotBlank(message = "Details can't be blank")
    private  String details;

    private double locationLat;

    private double locationLng;

    @FutureOrPresent
    private LocalDate startDate;

    @FutureOrPresent
    private LocalDate endDate;

    private String maxCapacity;


    @AssertTrue
    public boolean isValidDate(){
        return endDate.compareTo(startDate)>=0;
    }
}