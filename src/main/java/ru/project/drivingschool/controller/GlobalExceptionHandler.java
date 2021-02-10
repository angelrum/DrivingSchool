package ru.project.drivingschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
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

    @Autowired
    private JwtTokenRepository tokenRepository;

    @ExceptionHandler(AuthenticationException.class)
    public ErrorInfo handleAuthenticationException(AuthenticationException ex, HttpServletRequest request, HttpServletResponse response){
        //response.reset(); на фронте отображается ошибка CORS ERROR
        this.tokenRepository.clearToken(response);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return getCommonError(request, ErrorType.AUTHORIZARION_FAILED, ErrorType.AUTHORIZARION_FAILED.getErrorCode());
    }

    @ExceptionHandler(SessionAuthenticationException.class)
    public ErrorInfo handleAuthenticationException(SessionAuthenticationException ex, HttpServletRequest request, HttpServletResponse response){
        //response.reset();
        this.tokenRepository.clearToken(response);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return getCommonError(request, ErrorType.AUTHORIZARION_FAILED, "session.invalid");
    }

    private ErrorInfo getCommonError(HttpServletRequest request, ErrorType type, String... details) {
        return new ErrorInfo(
                UrlUtils.buildFullRequestUrl(request),
                type,
                type.getErrorCode(),
                details);
    }
}
