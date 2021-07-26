package ru.project.drivingschool.security;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.project.drivingschool.AuthorizedUser;
import ru.project.drivingschool.DrivingSchoolSecurityConfig;
import ru.project.drivingschool.service.UserService;

import static java.util.Objects.requireNonNull;

public class SecurityUtil {

    //private final AuthorizedUser authorizedUser;

    private static SecurityUtil instance;

    public SecurityUtil() {
    }

    private static final long TEST_ID = 1_000L;


//    public static AuthorizedUser safeGet() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth == null) {
//            return null;
//        }
//        Object principal = auth.getPrincipal();
//        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
//    }

//    public static AuthorizedUser get() {
//        AuthorizedUser user = safeGet();
//        requireNonNull(user, "No authorized user found");
//        return user;
//    }
//
    public static long authUserId() {
        return TEST_ID;
    }

    public static SecurityUtil getInstance() {
        if (SecurityUtil.instance == null)
            SecurityUtil.instance = new SecurityUtil();
        return SecurityUtil.instance;
    }

}