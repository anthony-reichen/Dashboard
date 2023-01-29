package dashboard.dashboardbackend.tests;

import org.springframework.boot.web.client.RestTemplateBuilder;
import com.google.gson.*;

import dashboard.dashboardbackend.models.WeatherReport;
import dashboard.dashboardbackend.models.WeatherForecast;
import dashboard.dashboardbackend.models.received.ReceivedWeatherCurrent;
import dashboard.dashboardbackend.models.received.ReceivedWeatherForecast;
import dashboard.dashboardbackend.services.WeatherService;

public class TestWeatherService {
	public static void main(String[] args) {

		WeatherService testRequete = new WeatherService();
		WeatherReport wr = testRequete.getWeatherReport("75056");

		System.out.println(wr);

		/*
		WeatherCurrentReport res = wR.toReport();
		
		System.out.println(res);
		
		Gson gson2 = new Gson();
		String json2 = gson2.toJson(res);
		System.out.println("\n"+json2);
		*/
	}
	

}
