package com.saucelabs.dto.identity;

import lombok.Data;

@Data
public class UserBuilder {
    public static final String STANDARD_USERNAME = "standard_user";
    public static final String LOCKED_OUT_USERNAME = "locked_out_user";
    public static final String PROBLEM_USERNAME = "problem_user";
    public static final String PASSWORD = "secret_sauce";
    protected String username;
    protected String password;

    protected UserBuilder() {
    }

    public static UserBuilder newInstance() {
        return new UserBuilder();
    }

    public UserBuilder standardUser() {
        this.username = STANDARD_USERNAME;
        this.password = PASSWORD;
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
        return this;
    }

    public User build() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
