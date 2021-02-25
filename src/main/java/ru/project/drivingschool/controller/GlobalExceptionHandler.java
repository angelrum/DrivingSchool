package ru.project.drivingschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.csrf.CsrfException;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.project.drivingschool.security.JwtTokenRepository;
import ru.project.drivingschool.util.exception.ErrorInfo;
import ru.project.drivingschool.util.exception.ErrorType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private JwtTokenRepository tokenRepository;

    public GlobalExceptionHandler(JwtTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @ExceptionHandler(AuthenticationException.class)
    public ErrorInfo handleAuthenticationException(AuthenticationException ex, HttpServletRequest request, HttpServletResponse response){
        //response.reset(); на фронте отображается ошибка CORS ERROR
        return commonAuthError(request, response, ErrorType.AUTHORIZARION_FAILED.getErrorCode());
    }

    @ExceptionHandler({MissingCsrfTokenException.class, InvalidCsrfTokenException.class})
    public ErrorInfo handleCsrfException(CsrfException ex, HttpServletRequest request, HttpServletResponse response) {
        return commonAuthError(request, response, ErrorType.AUTHORIZARION_FAILED.getErrorCode());
    }

    @ExceptionHandler(SessionAuthenticationException.class)
    public ErrorInfo handleAuthenticationException(SessionAuthenticationException ex, HttpServletRequest request, HttpServletResponse response){
        //response.reset();
        return commonAuthError(request, response, "session.invalid");
    }

    private ErrorInfo commonAuthError(HttpServletRequest request, HttpServletResponse response, String... message) {
        this.tokenRepository.clearToken(response);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return getCommonError(request, ErrorType.AUTHORIZARION_FAILED, message);
    }

    private ErrorInfo getCommonError(HttpServletRequest request, ErrorType type, String... details) {
        return new ErrorInfo(
                UrlUtils.buildFullRequestUrl(request),
                type,
                type.getErrorCode(),
                details);
    }
}
