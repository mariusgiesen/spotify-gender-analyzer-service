package com.github.mariusgiesen.spotifygenderanalyzerservice.security;

import com.github.mariusgiesen.spotifygenderanalyzerservice.services.SpotifyApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private final SpotifyApiService spotifyApiService;

    public CustomAuthenticationSuccessHandler(SpotifyApiService spotifyApiService) {
        this.spotifyApiService = spotifyApiService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        spotifyApiService.createForUser(authentication);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
