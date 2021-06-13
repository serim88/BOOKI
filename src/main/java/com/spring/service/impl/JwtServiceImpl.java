package com.spring.service.impl;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JwtServiceImpl {

	public static final String KEY = "testKey";
	
	public static final long EXPIREDTIME = 1000 * 60 * 60; // 1시간
	
	public static String createToken(String id) {
		Claims claims = Jwts.claims().setSubject(id);
		Date now = new Date();
		now.setTime(now.getTime() + EXPIREDTIME);
		
		String jwt = Jwts.builder()
        .setClaims(claims) // 정보 저장
        .setIssuedAt(now) // 토큰 발행 시간 정보
        .setExpiration(now) // set Expire Time 
        .signWith(SignatureAlgorithm.HS256, KEY)  // 사용할 암호화 알고리즘과 
        .compact();
		return jwt;
	}
	
	// 토큰에서 회원 아이디 추출
    public static String getUserId(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody().getSubject();
    }

	// 토큰의 유효성 + 만료일자 확인
    public static boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

	public static String signoutToken(String token) {
		// TODO Auto-generated method stub
		Claims claims = Jwts.claims().setSubject(Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody().getSubject());
		Date now = new Date();
		now.setTime(now.getTime() -100000);
		
		String jwt = Jwts.builder()
        .setClaims(claims) // 정보 저장
        .setIssuedAt(now) // 토큰 발행 시간 정보
        .setExpiration(now) // set Expire Time
        .signWith(SignatureAlgorithm.HS256, KEY)  // 사용할 암호화 알고리즘과 
        .compact();
		return jwt;
	}
    
}