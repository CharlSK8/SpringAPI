package com.platzi.market.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Deprecated
public class JWTUtil {

    // The secret key that is used to sign the token.
    private static final String KEY = "RSASSAPKCS1v15RSASSAPKCS1v15RSASSAPKCS1v15RSASSAPKCS1v15";

    /**
     * We are creating a JWT token with the username as the subject, the current date as the issued date, and the current
     * date plus one hour as the expiration date. We are signing the token with the HS256 algorithm and the secret key
     *
     * @param userdetails This is the user object that contains the username and password.
     * @return A JWT token
     */
    public String generateToken(UserDetails userdetails) {
        return Jwts.builder()
                    .setSubject(userdetails.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 1 ))
                    .signWith(SignatureAlgorithm.HS256, KEY)
                    .compact();
    }

    /**
     * > The token is valid if the username in the token matches the username in the userDetails object and the token is
     * not expired
     *
     * @param token The JWT token to validate
     * @param userDetails The user details object that contains the username and password of the user.
     * @return A boolean value.
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);
    }

    /**
     * It takes a token, gets the claims from the token, and returns the subject of the claims
     *
     * @param token The token that you want to extract the claims from.
     * @return The subject of the token.
     */
    public String extractUsername(String token){
        return getCalaims(token).getSubject();
    }


    /**
     * If the expiration date of the token is before the current date, then the token is expired
     *
     * @param token The token to be verified
     * @return A boolean value.
     */
    public boolean isTokenExpired(String token){
        return getCalaims(token).getExpiration().before(new Date());
    }

    /**
     * It takes a token, and returns the claims
     *
     * @param token The token to be parsed.
     * @return The claims object is being returned.
     */
    private Claims getCalaims(String token){
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();

    }
}
