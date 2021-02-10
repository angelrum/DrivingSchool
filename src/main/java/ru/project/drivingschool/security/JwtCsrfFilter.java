package ru.project.drivingschool.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.csrf.*;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class JwtCsrfFilter extends OncePerRequestFilter {

    private final CsrfTokenRepository tokenRepository;

    private final AccessDeniedHandler accessDeniedHandler;

    private final HandlerExceptionResolver resolver;

    public static final String CSRF_NAME = "x-csrf-token";

    public static final String CSRF_PARAMETER = "_csrf";

    public JwtCsrfFilter(CsrfTokenRepository csrfTokenRepository, HandlerExceptionResolver resolver) {
        this.accessDeniedHandler = new AccessDeniedHandlerImpl();
        Assert.notNull(csrfTokenRepository, "csrfTokenRepository cannot be null");
        this.tokenRepository = csrfTokenRepository;
        this.resolver = resolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        request.setAttribute(HttpServletResponse.class.getName(), response);
        CsrfToken csrfToken = this.tokenRepository.loadToken(request);
        boolean missingToken = csrfToken == null;
        if (missingToken) {
            csrfToken = this.tokenRepository.generateToken(request);
            this.tokenRepository.saveToken(csrfToken, request, response);
        }

        request.setAttribute(CsrfToken.class.getName(), csrfToken);
        request.setAttribute(csrfToken.getParameterName(), csrfToken);
        if (request.getServletPath().equals("/auth/login")) {
            try {
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                this.accessDeniedHandler.handle(request, response, new MissingCsrfTokenException(csrfToken.getToken()));
            }
        } else {
            String actualToken = request.getHeader(csrfToken.getHeaderName());
            if (actualToken == null) {
                actualToken = request.getParameter(csrfToken.getParameterName());
            }
            try {
                if (!StringUtils.isEmpty(actualToken)) {
                    Jwts.parser()
                            .setSigningKey(((JwtTokenRepository) tokenRepository).getSecret())
                            .parseClaimsJws(actualToken);
                    if (Objects.isNull(SecurityUtil.safeGet())){
                        this.resolver.resolveException(request, response, null, new SessionAuthenticationException("Session is invalid"));
                    } else
                        filterChain.doFilter(request, response);
                } else
                    this.accessDeniedHandler.handle(request, response, new InvalidCsrfTokenException(csrfToken, actualToken));
            } catch (JwtException e) {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("Invalid CSRF token found for " + UrlUtils.buildFullRequestUrl(request));
                }

                if (missingToken) {
                    this.accessDeniedHandler.handle(request, response, new MissingCsrfTokenException(actualToken));
                } else {
                    this.accessDeniedHandler.handle(request, response, new InvalidCsrfTokenException(csrfToken, actualToken));
                }
            }
        }
    }

}
