package com.harrytmthy.data.authentication.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version TokenResult, v 0.1 2019-12-10 21:17 by Harry Timothy
 */
@Getter @Setter
public class TokenResult {

    private boolean success;

    @SerializedName("expires_at") private String expiresAt;

    @SerializedName("request_token") private String requestToken;

}