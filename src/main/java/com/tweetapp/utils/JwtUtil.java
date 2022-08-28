package com.tweetapp.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tweetapp.model.UserRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service	
@Configurable
public class JwtUtil {

	@Value("${app.secret}")
	private String secret;
	
	SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	//6. validate username in token and database, expDate
	public boolean validateToken(String token,String loginId ) {
		String tokenUserName=getLoginId(token);
		return (loginId.equals(tokenUserName) && !isTokenExpired(token));
	}
	//5. Validate expiration Date
	public boolean isTokenExpired(String token) {
		Date expDate=getExpiryDate(token);
		return expDate.before(new Date(System.currentTimeMillis()));
	}
	
	//4. Read subject/username
	public String getLoginId(String token) {
		return getClaims(token).getSubject();
	}
	
	//3. Read Expiry date
	public Date getExpiryDate(String token) {
		return getClaims(token).getExpiration();
	}
	
	//2. Read token data
	public Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody();
	}
	
	//1. Generate token
	public String generateToken(String subject)
	{
		UserRequest userRequest=new UserRequest();
		return Jwts.builder()
				.setSubject(subject)
				.setIssuer("TweetApp")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.claim("loginId", userRequest.getLoginId())
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(60)))
				.signWith(key)
				.compact();
	}
}
