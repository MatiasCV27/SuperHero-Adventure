package com.example.demo.util;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginSuccessMessage extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        SessionFlashMapManager fManager = new SessionFlashMapManager();
        FlashMap fMap = new FlashMap();

        fMap.put("success", "Felicidades, usted inicio sesión correctamente");
        fManager.saveOutputFlashMap(fMap, request, response);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
