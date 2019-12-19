package com.harrytmthy.domain.authentication.model;

import com.google.gson.annotations.SerializedName;

import com.harrytmthy.domain.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version SessionParam, v 0.1 2019-12-17 14:13 by Harry Timothy
 */
@Getter @Setter
public class SessionParam implements BaseParam {

    @SerializedName("request_token") private String requestToken;

}