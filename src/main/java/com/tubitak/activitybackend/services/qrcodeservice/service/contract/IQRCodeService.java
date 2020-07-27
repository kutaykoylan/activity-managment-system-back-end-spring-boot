package com.tubitak.activitybackend.services.qrcodeservice.service.contract;

import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import com.tubitak.activitybackend.services.userservice.common.data.entitity.User;

import java.awt.image.BufferedImage;

public interface IQRCodeService {
    BufferedImage generateQRCode(User user, Activity activity);
}
