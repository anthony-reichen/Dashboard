package dashboard.dashboardbackend.controllers;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import dashboard.dashboardbackend.models.WeatherForecast;
import dashboard.dashboardbackend.models.WeatherReport;
import dashboard.dashboardbackend.models.response.ErrorResponse;
import dashboard.dashboardbackend.models.response.StandardResponse;
import dashboard.dashboardbackend.services.WeatherService;

@RestController
@CrossOrigin(origins = "http://docker-apache-1:80")
@RequestMapping("/weather")
public class WeatherController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private String format = "LOG => %s";

    @Autowired
    private WeatherService weatherService = new WeatherService();

    /**
     * The getWeatherReport function return a weather report constructed from external API.
     *
     * @return A WeatherReport object.
     */
    @GetMapping(value = "/report/{insee}")
    public ResponseEntity<? extends StandardResponse> getWeatherReport(@PathVariable String insee) {
        logger.info(String.format(format, "Getting weather report."));
        try {
        	return new ResponseEntity<WeatherReport>(weatherService.getWeatherReport(insee), HttpStatus.OK);
        }catch(RuntimeException e) {
        	return new ResponseEntity<ErrorResponse>(new ErrorResponse("Couldn't get city"), HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * The getWeatherForecast function retrieves the weather forecast for a given city.
     * 
     * @param String Specify the city name
     *
     * @return A weatherforecast object
     */
    @GetMapping(value = "/forecast/{insee}")
    public ResponseEntity<? extends StandardResponse> getWeatherForecast(@PathVariable String insee){
    	logger.info(String.format(format, "Getting weather forecast."));
    	try {
    		return new ResponseEntity<WeatherForecast>(weatherService.getForecastWeather(insee), HttpStatus.OK);
    	}catch(RuntimeException e) {
        	return new ResponseEntity<ErrorResponse>(new ErrorResponse("Couldn't get city"), HttpStatus.NOT_FOUND);
        }
    }
}