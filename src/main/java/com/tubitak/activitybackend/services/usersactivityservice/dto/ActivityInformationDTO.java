package com.tubitak.activitybackend.services.usersactivityservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@Builder
public class ActivityInformationDTO {

    private final String title;

    private final String details;

    private final double locationLat;

    private final double locationLng;

    private final LocalDate startDate;

    private final LocalDate endDate;

    public ActivityInformationDTO(@JsonProperty("title")String title,
                                  @JsonProperty("details")String details,
                                  @JsonProperty("locationLat")double locationLat,
                                  @JsonProperty("locationLng")double locationLng,
                                  @JsonProperty("startDate")LocalDate startDate,
                                  @JsonProperty("endDate")LocalDate endDate) {
        this.title = title;
        this.details = details;
        this.locationLat = locationLat;
        this.locationLng = locationLng;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
