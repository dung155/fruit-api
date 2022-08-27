package com.example.project_ogini.util;

import com.example.project_ogini.model.entities.UserDetail;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.function.Function;


@Component
@Slf4j
public class JWTUtil {
    private static String SECRET = "nguyen van dien";
    // thời gian hiệu lực
    private final long EXPIRATION_TIME = 604800000L;
    // prefix token
    //private final String TOKEN_PREFIX = "Bearer ";
    // header option
    //private final String HEADER_STRING = "Authorization";

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    // tạo jwt
    public String generateToken(UserDetail userDetail) {

        //Map<String, Object> claims = new HashMap<>();

        Date date = new Date();

        Date expiryDate = new Date(date.getTime()+ EXPIRATION_TIME);

        // tạo jwt từ id user
        String jwt = "";
        try {
            jwt = Jwts.builder()
                    .setSubject(Integer.toString(userDetail.getUser().getId()))
                    .setIssuedAt(date)
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS512, SECRET.getBytes("UTF-8"))
                    .compact();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return jwt;
    }

    // Lấy thông tin user từ jwt
    public Integer getUserByIdfromJWT(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET.getBytes("UTF-8"))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
                 | IllegalArgumentException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(claims.getSubject());
    }

    // validate jwt
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET.getBytes("UTF-8")).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        }catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty.");
        } catch (SignatureException e) {
            System.out.println("Signature Exception");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported Encoding Exception");
        }
        return false;
    }
}
