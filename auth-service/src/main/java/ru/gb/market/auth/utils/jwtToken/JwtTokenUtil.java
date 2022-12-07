package ru.gb.market.auth.utils.jwtToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    private String secret;//секрет для токена беремя из файла application.yaml

    @Value("${jwt.lifetime}")
    private Integer jwtLifetime;//время жизни токена беремя из файла application.yaml

    public String generateToken(UserDetails userDetails) {//метод для генерации токена
        Map<String, Object> claims = new HashMap<>();//требования
        List<String> rolesList = userDetails.getAuthorities().stream()//забираем все наши права из GrantedAuthority и добавляем в List
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles", rolesList);//закидываем все наши права в "claims"

        Date issuedDate = new Date();//фиксируем момент создания токена
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifetime);//время когда токен "протухнет"
        return Jwts.builder()//начинаем собирать токен
                .setClaims(claims)//добавляем свои "claims"
                .setSubject(userDetails.getUsername())//добавляем имя пользователя
                .setIssuedAt(issuedDate)//добавляем время создание токена
                .setExpiration(expiredDate)//добавляем время когда токен "стухнет"
                .signWith(SignatureAlgorithm.HS256, secret)//хэшируем по алгоритму SignatureAlgorithm.HS256 используя секрет
                .compact();//завершаем сборку токена
    }

    public String getUsernameFromToken(String token) {//метод для получения имени пользователя из токена
        return getClaimFromToken(token, Claims::getSubject);
    }

    public List<String> getRoles(String token) {//метод для получения ролей из токена
        return getClaimFromToken(token, (Function<Claims, List<String>>) claims -> claims.get("roles", List.class));
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {//универсальный метод для получения определенной информации из токена
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);//реализация функционального интерфейса
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()//парсим токен
                .setSigningKey(secret)//отдаем секрет
                .parseClaimsJws(token)//отдаем токен
                .getBody();//получаем "claims" из токена
    }
}
