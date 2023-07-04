package com.cf;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootDemo1ApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 生成JWT
     */
    @Test
    public void testGenJWT(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","tom");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"ChenFang")
                .setClaims(claims)//设置自定义的部分（载荷）
                .setExpiration(new Date(System.currentTimeMillis()+3600 *1000))
                .compact();
        System.out.println(jwt);
    }

    /**
     * 解析JWT
     */
    @Test
    public void testParseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("ChenFang")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTY4MDQyNTAzOH0.BQwE2UXUWNNESO-BoUKcbHJjiPQ8TJdfni4a6vp9cx4")
                .getBody();
        System.out.println(claims);
    }
}
