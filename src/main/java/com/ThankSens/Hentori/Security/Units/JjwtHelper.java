package com.ThankSens.Hentori.Security.Units;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;


@Component
public class


JjwtHelper {
    private String txtPrivate = "dGhhbmhzYW5nOTdxdWFkZXB0cmFpdmFub2l0aWVuZ3RoYXRsYW5nYXVkZXQ";

    public String createJwt(String json){
        byte[] bytes = Decoders.BASE64URL.decode(txtPrivate);
        SecretKey key = Keys.hmacShaKeyFor(bytes);
        String jwt = Jwts.builder().subject(json).signWith(key).compact();
        return jwt;
    }

    public String authenticateJwt(String jwtString){
        byte[] bytes = Decoders.BASE64URL.decode(txtPrivate);
        SecretKey key = Keys.hmacShaKeyFor(bytes);
        try {
            Jws<Claims> claimsJws = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwtString);
            String jsonAdmin = claimsJws.getPayload().getSubject();
            return jsonAdmin;
        }catch (Exception e){
            return null;
        }


    }
}
