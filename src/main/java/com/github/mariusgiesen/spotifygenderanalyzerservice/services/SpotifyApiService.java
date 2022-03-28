package com.github.mariusgiesen.spotifygenderanalyzerservice.services;

import com.wrapper.spotify.SpotifyApi;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SpotifyApiService {

    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    private final Map<Authentication, SpotifyApi> authenticationToSpotifyApi = new ConcurrentHashMap<>();

    public SpotifyApiService(OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    }

    public SpotifyApi createForUser(Authentication authentication) {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

        OAuth2AuthorizedClient client = oAuth2AuthorizedClientService.loadAuthorizedClient(
                oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());

        String accessToken = client.getAccessToken().getTokenValue();

        SpotifyApi spotifyApi =  new SpotifyApi.Builder().setAccessToken(accessToken).build();

        return authenticationToSpotifyApi.put(authentication, spotifyApi);
    }

    public SpotifyApi getForUser(Authentication authentication) {
        return authenticationToSpotifyApi.get(authentication);
    }
}
