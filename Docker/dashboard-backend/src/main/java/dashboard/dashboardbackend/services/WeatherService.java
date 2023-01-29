package dashboard.dashboardbackend.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import dashboard.dashboardbackend.models.WeatherForecast;
import dashboard.dashboardbackend.models.WeatherReport;
import dashboard.dashboardbackend.models.received.ReceivedWeatherCurrent;
import dashboard.dashboardbackend.models.received.ReceivedWeatherForecast;

@Service
public class WeatherService {

	private final RestTemplate restTemplate;
	
	public WeatherService() {
		this.restTemplate = (new RestTemplateBuilder()).build();
	}
	
	public WeatherReport getWeatherReport(String insee) throws RuntimeException{
		
		String url = "https://api.meteo-concept.com/api/forecast/nextHours?token=415daf318d775b12f79249c49d4af30066d7cc610699f206c5f34bd2ee5b5493&hourly=true&insee="+insee;
		String json = this.restTemplate.getForObject(url, String.class);
		Gson gson = new Gson();
		ReceivedWeatherCurrent rwc = gson.fromJson(json, ReceivedWeatherCurrent.class);
		if(! rwc.getInseeCode().equals(insee)) {
			throw new RuntimeException("No such insee code.");
		}else {
			WeatherReport wr = rwc.toReport();
			return wr;			
		}
	}
	
	//faire une gestion des erreurs car ça renvoie tout le temps la météo de Rennes si l'insee n'est pas bon.
	
	public WeatherForecast getForecastWeather(String insee) {
		String url = "https://api.meteo-concept.com/api/forecast/daily?token=415daf318d775b12f79249c49d4af30066d7cc610699f206c5f34bd2ee5b5493&insee="+insee;
		String json = this.restTemplate.getForObject(url, String.class);
		Gson gson = new Gson();
		ReceivedWeatherForecast rwf = gson.fromJson(json, ReceivedWeatherForecast.class);
		if(! rwf.getInseeCode().equals(insee)) {
			throw new RuntimeException("No such insee code.");
		}else {
		WeatherForecast wf = rwf.toReport();
		return wf;
		}
	}
}
