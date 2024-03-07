package com.saucelabs.dto.identity;

import lombok.Data;

@Data
public class UserBuilder {

    public static final String STANDARD_USERNAME = "standard_user";
    public static final String USER_LASTNAME = "Bow";
    public static final String ZIP = "220000";
    public static final String LOCKED_OUT_USERNAME = "locked_out_user";
    public static final String PROBLEM_USERNAME = "problem_user";
    public static final String PASSWORD = "secret_sauce";
    public static final String PAYMENT_CARD = "SauceCard";
    protected String username;
    protected String password;
    protected String lastname;
    protected String zip;
    protected String paymentCard;

    public static UserBuilder newInstance() {
        return new UserBuilder();
    }

    public UserBuilder standardUser() {
        this.username = STANDARD_USERNAME;
        this.password = PASSWORD;
        this.lastname = USER_LASTNAME;
        this.zip = ZIP;
        this.paymentCard = PAYMENT_CARD;
        return this;
    }

    public UserBuilder lockedOutUser() {
        this.username = LOCKED_OUT_USERNAME;
        this.password = PASSWORD;
        return this;
    }

    public UserBuilder problemUser() {
        this.username = PROBLEM_USERNAME;
        this.password = PASSWORD;
        this.lastname = USER_LASTNAME;
        this.zip = ZIP;
        this.paymentCard = PAYMENT_CARD;
        return this;
    }

    public User build() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setLastName(lastname);
        user.setZipCode(zip);
        user.setPaymentCard(paymentCard);
        return user;
    }
}
