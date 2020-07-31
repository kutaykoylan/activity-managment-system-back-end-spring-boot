package com.tubitak.activitybackend.services.mailservice.util;

import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import com.tubitak.activitybackend.services.userservice.common.data.entitity.User;

public class InformationStringGenerator {
    public static String getRegistrationInformation(User user , Activity activity){
        return "{ " +
                "\"activity\": {  \"title\": \""+activity.getTitle()+"\" ," +
                " \"details\": \""+activity.getDetails()+"\" ," +
                " \"startDate\" : \""+activity.getStartDate()+"\"," +
                "  \"endDate\" :\""+activity.getEndDate()+"\"," +
                "\"locationLat\": \""+activity.getLocationLat()+"\" , " +
                "\"locationLng\": \""+activity.getLocationLat()+"\" }," +
                "\"user\":{ " +
                /*"\"tcSecurityNumber\": \""+user.getTcSecurityNumber()+"\" ," +
                " \"name\": \""+user.getName()+"\" ," +
                " \"surname\" : \""+user.getSurname()+"\"," +
                "  \"email\" :\""+user.getEmail()+"\"," +*/
                "\"username\": \""+user.getUsername()+"\"}";
    }
    /*
     "\"birthDate\": \""+user.getBirthDate()+"\"" +
                ", \"address\": \""+user.getAddress()+"\" 
     */
}
