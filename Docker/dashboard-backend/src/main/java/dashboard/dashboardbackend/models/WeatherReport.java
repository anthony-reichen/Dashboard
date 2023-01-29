package dashboard.dashboardbackend.models;

import dashboard.dashboardbackend.models.response.StandardResponse;
import dashboard.dashboardbackend.utils.*;

public class WeatherReport extends StandardResponse{
	
	private String city;
	private int temp;
	private int weather;
	private String weatherVerbose;
	private String windDirection;
	private int windSpeed;
	
	public WeatherReport(String city, int temp, int weather, int windSpeed, int windDirection) {
		super(true, "Weather data has been updated for city "+city);
		this.city = city;
		this.temp = temp;
		this.weather = weather;
		this.weatherVerbose = WeatherUtils.weatherToString(weather);
		this.windDirection = WeatherUtils.degreeToEightAxis(windDirection);
		this.windSpeed = windSpeed;
	}
	
	@Override
	public String toString() {
		String str = new String("Ville: "+this.city+"\n"
				+"Température: "+this.temp+"\n"
				+"Météo: "+this.weather+"\n"
				+"Wind: "+this.windSpeed+" - "+this.windDirection);
		return str;
	}

	public String getCity() {
		return city;
	}

	public int getTemp() {
		return temp;
	}

	public int getWeather() {
		return weather;
	}

	public String getWeatherVerbose() {
		return weatherVerbose;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public int getWindSpeed() {
		return windSpeed;
	}
	
}
