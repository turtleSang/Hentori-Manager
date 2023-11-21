package com.ThankSens.Hentori.Security.Units;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class JjwtHelper {
    private String txtPrivate = "dGhhbmhzYW5nOTdxdWFkZXB0cmFpdmFub2l0aWVuZ3RoYXRsYW5nYXVkZXQ";

    public String createJwt(String json){
        byte[] bytes = Decoders.BASE64URL.decode(txtPrivate);
        SecretKey key = Keys.hmacShaKeyFor(bytes);
        String jwt = Jwts.builder().subject(json).signWith(key).compact();
        return jwt;
    }

}
