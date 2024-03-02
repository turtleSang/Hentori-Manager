package com.ThankSens.Hentori.Security.Units;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


@Component
public class JjwtHelper {
    private String txtPrivate = "dGhhbmhzYW5nOTdxdWFkZXB0cmFpdmFub2l0aWVuZ3RoYXRsYW5nYXVkZXQ";

    public String createJwt(String json){
        byte[] bytes = Decoders.BASE64URL.decode(txtPrivate);
        SecretKey key = Keys.hmacShaKeyFor(bytes);
        LocalDateTime expireDate = LocalDateTime.now().plusHours(2);
        Date expire = Date.from(expireDate.atZone(ZoneId.systemDefault()).toInstant());
        String jwt = Jwts.builder().subject(json).signWith(key).expiration(expire).compact();
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
