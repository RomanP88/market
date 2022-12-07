package ru.gb.market.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.gb.market.api.exception.MarketException;
import ru.gb.market.auth.dto.AuthRequest;
import ru.gb.market.auth.dto.AuthResponse;
import ru.gb.market.auth.services.UserService;
import ru.gb.market.auth.utils.jwtToken.JwtTokenUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {
    @Value("${jwt.secret}")
    private String secret;//секрет для токена беремя из файла application.yaml
    private final UserService userService; //имплиментируем UserService
    private final JwtTokenUtil jwtTokenUtil;//имплиментируем jwtTokenUtil
    private final AuthenticationManager authenticationManager;//имплиментируем authenticationManager
    private final RestTemplate restTemplate;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {//создаем токен
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));// проверяем аутификацию пользователя,если выполнилось без ошибок, значит пользователь аутифицирован
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new MarketException("Incorrect username or password"), HttpStatus.UNAUTHORIZED);//если пользователь не существует возвращаем сообщение об ошибке
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());//создаем объект UserDetails содержащий инфрмацию о пользователе
        String token = jwtTokenUtil.generateToken(userDetails);//генерируем токен
        return ResponseEntity.ok(new AuthResponse(token));//возвращаем токен, и на клиентской стороне подшиваем его в Header
    }
    @PostMapping("/auth/keycloak")
    public ResponseEntity<?> createTokenFromKeycloak(@RequestBody AuthRequest authRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type","password");
        map.add("client_id","market-client");
        map.add("client_secret",secret);
        map.add("scope","profile");
        map.add("username", authRequest.getUsername());
        map.add("password", authRequest.getPassword());
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<LinkedHashMap<String, ?>> response =
                restTemplate.exchange("http://localhost:8090/realms/market-realm/protocol/openid-connect/token",
                        HttpMethod.POST,
                        entity,
                        new ParameterizedTypeReference<LinkedHashMap<String, ?>>() {},
                        LinkedHashMap.class);
        String token = (String) Objects.requireNonNull(response.getBody()).get("access_token");
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
