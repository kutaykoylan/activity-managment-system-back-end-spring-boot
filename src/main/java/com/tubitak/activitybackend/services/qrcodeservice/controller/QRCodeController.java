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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/QR")
@RequiredArgsConstructor
public class QRCodeController {

    private final IQRCodeService iqrCodeService;
    private final IActivityMapperForQRCodeService activityMapperForQRCodeService;
    private final IUserMapperForQRCodeService userMapperForQRCodeService;

    @PostMapping
    public ResponseEntity<byte[]> getRegisterDetailsAsQRCode(@RequestBody QRCodeDTO qrCodeDTO){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedImage bufferedImage = iqrCodeService.generateQRCode(userMapperForQRCodeService.mapToEntity(qrCodeDTO.getUserDTO()),activityMapperForQRCodeService.mapToEntity(qrCodeDTO.getActivityDTO()));
        try {
            ImageIO.write(bufferedImage, "jpg", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();
        return new ResponseEntity<>(bytes, HttpStatus.OK);
    }

    private static byte[] toByteArrayAutoClosable(BufferedImage image, String type) throws IOException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()){
            ImageIO.write(image, type, out);
            return out.toByteArray();
        }
    }
}
