package com.tubitak.activitybackend.services.mailservice.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MailDTO {

    private UserDTO userDTO;
    private ActivityDTO activityDTO;

}
