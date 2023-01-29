package dashboard.dashboardbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Recommendations;
import se.michaelthelin.spotify.model_objects.specification.SavedTrack;
import se.michaelthelin.spotify.model_objects.specification.Track;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import org.apache.hc.core5.http.ParseException;

import dashboard.dashboardbackend.models.response.ErrorResponse;
import dashboard.dashboardbackend.models.response.TokenResponse;
import dashboard.dashboardbackend.services.SpotifyService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/spotify")
public class SpotifyController {

    @Autowired
    private SpotifyService spotifyService;

    /**
     * The spotifyLogin function is used to redirect the user to Spotify's login
     * page.
     * 
     * @return A responseEntity which is a wrapper for a uri
     */
    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity<URI> spotifyLogin() {
        return new ResponseEntity<URI>(spotifyService.login(), HttpStatus.OK);
    }

    /**
     * The getSpotifyUserCode function is used to get the user code from Spotify.
     * 
     * @param String              Get the access token from spotify
     * @param HttpServletResponse Redirect the user to the spotify login page
     *
     * @return A string which is the code to be used by the user in order to obtain
     *         access and refresh tokens
     */
    @GetMapping("/get-user-code")
    public ResponseEntity<?> getSpotifyUserCode(@RequestParam("code") String userCode, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("http://localhost:80/");
        try {
            String code = spotifyService.getUserCode(userCode);
            return new ResponseEntity<TokenResponse>(new TokenResponse(code), HttpStatus.OK);
        } catch (IOException | ParseException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (SpotifyWebApiException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * The getUserTopTracks function returns the top tracks of a user.
     * 
     * @return An array of track objects
     */
    @GetMapping("/top-tracks")
    public ResponseEntity<?> getUserTopTracks() {
        try {
            return new ResponseEntity<Track[]>(spotifyService.getUserTopTracks(), HttpStatus.OK);
        } catch (IOException | ParseException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (SpotifyWebApiException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * The getUserSavedTracks function returns a list of the user's saved tracks.
     * 
     * @return An array of the user saved track objects
     */
    @GetMapping("/saved-tracks")
    public ResponseEntity<?> getUserSavedTracks() {
        try {
            return new ResponseEntity<SavedTrack[]>(spotifyService.getUserSavedTracks(), HttpStatus.OK);
        } catch (IOException | ParseException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (SpotifyWebApiException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/recommendations")
    public ResponseEntity<?> getUserRecommendations() {
        try {
            return new ResponseEntity<Recommendations>(spotifyService.getRecommendations(), HttpStatus.OK);
        } catch (IOException | ParseException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (SpotifyWebApiException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * The addTrackToQueue function adds a track to the queue.
     * 
     * @param String Specify the uri of a track to be added to the queue
     * @return A responseentity&lt;errorresponse&gt;
     */
    @PutMapping("/add-queue/{trackUri}")
    public ResponseEntity<?> addTrackToQueue(@PathVariable String trackUri) {
        try {
            spotifyService.addTrackToQueue(trackUri);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
