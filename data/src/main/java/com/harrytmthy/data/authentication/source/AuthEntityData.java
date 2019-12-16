package com.harrytmthy.data.authentication.source;

import com.harrytmthy.data.authentication.model.AuthResult;
import com.harrytmthy.data.common.DataConstants;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AuthEntityData, v 0.1 2019-12-10 20:54 by Harry Timothy
 */
public interface AuthEntityData {

    @GET(DataConstants.API_NEW_TOKEN)
    Observable<AuthResult> authenticate();

}
