package com.harrytmthy.data.common;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version PagedResult, v 0.1 2019-12-11 10:33 by Harry Timothy
 */

@Getter @Setter
public class PagedResult<R> {

    private int page;

    @SerializedName("total_results") private int totalResults;

    @SerializedName("total_pages") private int totalPages;

    private List<R> results;

}