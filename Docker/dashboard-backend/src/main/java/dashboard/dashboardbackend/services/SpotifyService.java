package dashboard.dashboardbackend.services;

import java.io.IOException;
import java.net.URI;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.hc.core5.http.ParseException;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Rand;
import org.springframework.stereotype.Service;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Recommendations;
import se.michaelthelin.spotify.model_objects.specification.SavedTrack;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import se.michaelthelin.spotify.requests.data.browse.GetRecommendationsRequest;
import se.michaelthelin.spotify.requests.data.browse.miscellaneous.GetAvailableGenreSeedsRequest;
import se.michaelthelin.spotify.requests.data.library.GetUsersSavedTracksRequest;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;
import se.michaelthelin.spotify.requests.data.player.AddItemToUsersPlaybackQueueRequest;

@Service
public class SpotifyService {
    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/spotify/get-user-code");

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId("<insert spotify token>")
            .setClientSecret("<insert spotify secret>")
            .setRedirectUri(redirectUri)
            .build();

    /**
     * The login function is used to get the authorization code from Spotify.
     * It uses the spotifyApi object to create an AuthorizationCodeUriRequest, which
     * is then executed and returns a URI.
     * The URI can be used in a browser for authentication purposes.
     * 
     * @return A uri object
     */
    public URI login() {
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
                .scope("user-read-private, user-read-email, user-library-read, playlist-read-private, user-top-read")
                .show_dialog(true).build();
        final URI uri = authorizationCodeUriRequest.execute();
        return uri;
    }

    /**
     * The getUserCode function is used to get the access and refresh tokens from
     * Spotify.
     * 
     * @param code Get the access and refresh tokens from spotify
     * @return An accessToken
     */
    public String getUserCode(String code) throws IOException, SpotifyWebApiException, ParseException {
        AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code).build();

        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
            System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            throw e;
        }
        return spotifyApi.getAccessToken();
    }

    /**
     * The getUserTopTracks function returns the top 10 tracks of a user.
     * 
     * @return An array of track objects
     */
    public Track[] getUserTopTracks() throws IOException, SpotifyWebApiException, ParseException {
        final GetUsersTopTracksRequest request = spotifyApi.getUsersTopTracks().limit(10).build();
        try {
            final Paging<Track> trackPaging = request.execute();
            return trackPaging.getItems();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            throw e;
        }
    }

    /**
     * The getUserSavedTracks function retrieves the saved tracks of a user.
     * 
     * @return An array of savedtrack objects
     */
    public SavedTrack[] getUserSavedTracks() throws IOException, SpotifyWebApiException, ParseException {
        final GetUsersSavedTracksRequest request = spotifyApi.getUsersSavedTracks().limit(30).build();
        try {
            final Paging<SavedTrack> trackPaging = request.execute();
            return trackPaging.getItems();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            throw e;
        }
    }

    public Recommendations getRecommendations() throws IOException, SpotifyWebApiException, ParseException {
        final GetAvailableGenreSeedsRequest getAvailableGenreSeedsRequest = spotifyApi.getAvailableGenreSeeds().build();
        Track[] topTracks = getUserTopTracks();
        Track seed_track = topTracks[new Random().nextInt(topTracks.length)];
        ArtistSimplified seed_artist = seed_track.getArtists()[0];
        String[] genres = getAvailableGenreSeedsRequest.execute();
        String genre_seed = "";
        for (int i = 1; i < 3; i++) {
            genre_seed += genres[new Random().nextInt(genres.length)] + ",";
        }
        System.out.println(genre_seed);

        final GetRecommendationsRequest getRecommendationsRequest = spotifyApi.getRecommendations().limit(10)
                .seed_artists(seed_artist.getId())
                .seed_genres(genre_seed.substring(0, genre_seed.length() - 1))
                .seed_tracks(seed_track.getId())
                .build();
        try {
            return getRecommendationsRequest.execute();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            throw e;
        }
    }

    /**
     * The addTrackToQueue function adds a track to the users playback queue.
     * 
     * @param trackUri Specify the uri of the track to be added to the queue
     * @return A response object
     */
    public void addTrackToQueue(String trackUri) throws IOException, SpotifyWebApiException, ParseException {
        final AddItemToUsersPlaybackQueueRequest addItemToUsersPlaybackQueueRequest = spotifyApi
                .addItemToUsersPlaybackQueue(trackUri).build();
        try {
            addItemToUsersPlaybackQueueRequest.execute();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            throw e;
        }
    }
}