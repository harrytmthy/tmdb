package com.harrytmthy.domain.authentication.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version Auth, v 0.1 2019-12-17 14:40 by Harry Timothy
 */
@Getter @Setter
public class Auth {

    private String requestToken;

    private String sessionId;

}