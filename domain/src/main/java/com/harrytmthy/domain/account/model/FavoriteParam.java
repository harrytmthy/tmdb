package com.harrytmthy.domain.account.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version FavoriteParam, v 0.1 2019-12-22 20:41 by Harry Timothy
 */
@Getter @Setter
public class FavoriteParam {

    @SerializedName("media_type") private String mediaType;

    @SerializedName("media_id") private int mediaId;

    private boolean favorite;

}