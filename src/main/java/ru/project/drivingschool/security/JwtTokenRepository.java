package ru.project.drivingschool.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Repository;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import static ru.project.drivingschool.security.JwtCsrfFilter.CSRF_NAME;
import static ru.project.drivingschool.security.JwtCsrfFilter.CSRF_PARAMETER;

@Repository
public class JwtTokenRepository implements CsrfTokenRepository {

    @Value("jwt.token.secret")
    @Getter private String secret;

    @Value("#{ T(java.lang.Long).parseLong('${jwt.token.timeout.min}')}")
    private Long timeout;

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String id = UUID.randomUUID().toString().replace("-", "");
        String headers = request.getHeader("Authorization");
        if (Objects.nonNull(headers)) id = headers.substring(6);
        Date now = new Date();
        Date exp = Date.from(LocalDateTime.now().plusMinutes(timeout)
                .atZone(ZoneId.systemDefault()).toInstant());

        String token = "";
        try {
            token = Jwts.builder()
                    .setId(id)
                    .setIssuedAt(now)
                    .setNotBefore(now)
                    .setExpiration(exp)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();
        } catch (JwtException e) {
            //ignore
        }
        return new DefaultCsrfToken(CSRF_NAME, CSRF_PARAMETER, token);
    }

    @Override
    public void saveToken(CsrfToken csrfToken, HttpServletRequest request, HttpServletResponse response) {
        if (Objects.nonNull(csrfToken))
            response.addHeader(csrfToken.getHeaderName(), csrfToken.getToken());
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }
}
