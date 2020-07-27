package com.tubitak.activitybackend.services.qrcodeservice.controller;

import com.tubitak.activitybackend.services.mailservice.util.InformationStringGenerator;
import com.tubitak.activitybackend.services.mailservice.util.QRCodeGenerator;
import com.tubitak.activitybackend.services.qrcodeservice.dto.QRCodeDTO;
import com.tubitak.activitybackend.services.qrcodeservice.mapper.IActivityMapperForQRCodeService;
import com.tubitak.activitybackend.services.qrcodeservice.mapper.IUserMapperForQRCodeService;
import com.tubitak.activitybackend.services.qrcodeservice.service.contract.IQRCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/QR")
@RequiredArgsConstructor
public class QRCodeController {

    private final IQRCodeService iqrCodeService;
    private final IActivityMapperForQRCodeService activityMapperForQRCodeService;
    private final IUserMapperForQRCodeService userMapperForQRCodeService;

    @GetMapping
    public ResponseEntity<BufferedImage> getRegisterDetailsAsQRCode(@RequestBody QRCodeDTO qrCodeDTO){
        return new ResponseEntity<>(iqrCodeService.generateQRCode(userMapperForQRCodeService.mapToEntity(qrCodeDTO.getUserDTO()),activityMapperForQRCodeService.mapToEntity(qrCodeDTO.getActivityDTO())), HttpStatus.OK);
    }
}
