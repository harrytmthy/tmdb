package com.harrytmthy.data.authentication.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AuthResult, v 0.1 2019-12-10 21:17 by Harry Timothy
 */
@Data public class AuthResult {

    private boolean success;
    @SerializedName("guest_session_id") private String guestSessionId;
    @SerializedName("expires_at") private String expiresAt;

}
