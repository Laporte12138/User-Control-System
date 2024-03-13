package com.tu.usercontrol.model.domain.request;

import lombok.Data;

import java.io.Serializable;
import java.io.StringReader;
@Data
public class UserRegisterRequest implements Serializable {

    public static final long serialVersionID = 3191241716373120793L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;

}
