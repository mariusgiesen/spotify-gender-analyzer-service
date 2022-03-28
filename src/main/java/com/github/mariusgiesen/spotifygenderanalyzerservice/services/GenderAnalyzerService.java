package com.github.mariusgiesen.spotifygenderanalyzerservice.services;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import org.apache.hc.core5.http.ParseException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GenderAnalyzerService {

    private final SpotifyApiService spotifyApiService;

    public GenderAnalyzerService(SpotifyApiService spotifyApiService) {
        this.spotifyApiService = spotifyApiService;
    }

    public Paging<PlaylistSimplified> getListOfUsersPlaylistsRequest(Authentication authentication) {
        try {
            return spotifyApiService.getForUser(authentication)
                    .getListOfUsersPlaylists(authentication.getName())
                    .build().execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Paging<PlaylistTrack> getItemsOfPlaylist(Authentication authentication, String playlistId) {
        try {
            return spotifyApiService.getForUser(authentication).getPlaylistsItems(playlistId).build().execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Artist[] getArtists(Authentication authentication, String[] artistIds) {
        try {
            return spotifyApiService.getForUser(authentication).getSeveralArtists(artistIds).build().execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
