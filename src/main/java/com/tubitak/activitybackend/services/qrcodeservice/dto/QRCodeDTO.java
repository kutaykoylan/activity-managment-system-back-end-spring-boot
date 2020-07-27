package com.tubitak.activitybackend.services.qrcodeservice.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QRCodeDTO {

    private UserDTO userDTO;
    private ActivityDTO activityDTO;

}
