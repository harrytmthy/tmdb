package com.harrytmthy.data.authentication.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version SessionResult, v 0.1 2019-12-17 14:01 by Harry Timothy
 */
@Getter @Setter
public class SessionResult {

    private boolean success;

    @SerializedName("session_id") private String sessionId;

}