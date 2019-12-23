package com.harrytmthy.data.account.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version StatusResult, v 0.1 2019-12-22 20:17 by Harry Timothy
 */
@Getter @Setter
public class StatusResult {

    @SerializedName("status_code") private int statusCode;

    @SerializedName("status_message") private String statusMessge;

}