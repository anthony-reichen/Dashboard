package dashboard.dashboardbackend.models.received;

import java.util.ArrayList;
import java.util.Date;

import dashboard.dashboardbackend.models.Forecast;
import dashboard.dashboardbackend.models.WeatherReport;
import dashboard.dashboardbackend.models.WeatherForecast;

public class ReceivedWeatherForecast {
	
	private City city;
	private String update;
	private ArrayList<Forecast> forecast;
	
	public ReceivedWeatherForecast() {
		
	}
	
	private class City{
		private String insee;
		private int cp;
		private String name;
		private double latitude;
		private double longitude;
		private int altitude;
	}

	public String getCity() {
		return this.city.name;
	}
	
	public WeatherForecast toReport() {
		return new WeatherForecast(this.city.name, this.forecast);
	}

	public String getInseeCode() {
		return city.insee;
	}
}
