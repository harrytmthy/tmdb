package com.harrytmthy.data.authentication.source;

import com.harrytmthy.data.authentication.model.SessionResult;
import com.harrytmthy.data.authentication.model.TokenResult;
import com.harrytmthy.data.constants.DataConstants;
import com.harrytmthy.domain.authentication.model.SessionParam;
import com.harrytmthy.domain.authentication.model.TokenParam;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AuthEntityData, v 0.1 2019-12-10 20:54 by Harry Timothy
 */
public interface AuthEntityData {

    @GET(DataConstants.API_NEW_TOKEN)
    Observable<TokenResult> createToken();

    @POST(DataConstants.API_VALIDATE_TOKEN)
    @Headers("Content-Type: application/json")
    Observable<TokenResult> validateToken(@Body TokenParam params);

    @POST(DataConstants.API_NEW_SESSION)
    @Headers("Content-Type: application/json")
    Observable<SessionResult> createSession(@Body SessionParam params);

}