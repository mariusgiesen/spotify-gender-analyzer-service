package com.github.mariusgiesen.spotifygenderanalyzerservice.controller;

import com.github.mariusgiesen.spotifygenderanalyzerservice.services.GenderAnalyzerService;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/test")
public class TestController {

    private final GenderAnalyzerService genderAnalyzerService;

    public TestController(GenderAnalyzerService genderAnalyzerService) {
        this.genderAnalyzerService = genderAnalyzerService;
    }

    @GetMapping("/me")
    public Principal user(Principal principal) {
        return principal;
    }

    @GetMapping("/playlists")
    public Paging<PlaylistSimplified> getUserPlaylists(Authentication authentication) {
        return genderAnalyzerService.getListOfUsersPlaylistsRequest(authentication);
    }

    @GetMapping("/playlists/{playlistId}")
    public Paging<PlaylistTrack> getUserPlaylists(Authentication authentication, @PathVariable("playlistId") String playlistId) {
        return genderAnalyzerService.getItemsOfPlaylist(authentication, playlistId);
    }

    @GetMapping("/artists/{artistId}")
    public Artist[] getArtists(Authentication authentication, @PathVariable("artistId") String artistId) {
        return genderAnalyzerService.getArtists(authentication, new String[]{artistId});
    }
}
