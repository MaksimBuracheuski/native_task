package com.mobile.saucelabs.dto.identity;

import lombok.Data;

@Data
public class User {

    public String username;
    public String password;
    public String lastName;
    public String zipCode;
    public String paymentCard;
}
