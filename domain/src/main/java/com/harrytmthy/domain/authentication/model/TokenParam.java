package com.harrytmthy.domain.authentication.model;

import com.google.gson.annotations.SerializedName;

import com.harrytmthy.domain.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version TokenParam, v 0.1 2019-12-17 14:27 by Harry Timothy
 */
@Getter @Setter
public class TokenParam implements BaseParam {

    private String username;

    private String password;

    @SerializedName("request_token") private String requestToken;

}