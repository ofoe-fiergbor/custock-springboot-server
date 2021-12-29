package com.davinci.custockspringbootserver.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

//    @Value("${system.jwt_secrete}")
    private static final String SECRETE_KEY= "super_secrete_key";
    private static final String SUBJECT = "User details";
    private static final String ISSUER = "cuStock";
    private static final String CLAIM = "email";

    public String createAccessToken(String email) {
        return JWT.create()
                .withSubject(SUBJECT)
                .withClaim(CLAIM, email)
                .withIssuedAt(new Date())
                .withIssuer(ISSUER)
                .sign(Algorithm.HMAC256(SECRETE_KEY.getBytes()));
    }

    public String validateToken(String token) throws JWTVerificationException {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRETE_KEY.getBytes()))
                .withSubject(SUBJECT)
                .withIssuer(ISSUER)
                .build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        return jwt.getClaim(CLAIM).asString();
    }





}
