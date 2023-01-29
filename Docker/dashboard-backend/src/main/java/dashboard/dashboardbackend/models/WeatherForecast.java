package dashboard.dashboardbackend.models;

import java.util.ArrayList;
import java.util.Date;

import dashboard.dashboardbackend.models.response.StandardResponse;
import dashboard.dashboardbackend.utils.*;

public class WeatherForecast extends StandardResponse{
	
	private String city;
	private ArrayList<SimpleForecast> forecast = new ArrayList<SimpleForecast>();
	
	public WeatherForecast(String city, ArrayList<Forecast> fc){
		super(true, "Weather data has been updated for city "+city);
		this.city = city;
		for(Forecast f : fc) {
			SimpleForecast sf = new SimpleForecast(f.getTmin(), f.getTmax(), f.getProbarain(), WeatherUtils.weatherToString(f.getWeather()), f.getWeather(), f.getDatetime());
			this.forecast.add(sf);
		}
	}
	
	public String getCity() {
		return city;
	}

	public ArrayList<SimpleForecast> getForecast() {
		return forecast;
	}

	private class SimpleForecast{
		private int tmin;
		private int tmax;
		private int probarain;
		private String weatherVerbose;
		private int weather;
		private Date date;
		
		public SimpleForecast(int tmin, int tmax, int probarain, String weatherVerbose, int weather, Date date) {
			this.tmin = tmin;
			this.tmax = tmax;
			this.probarain = probarain;
			this.weatherVerbose = weatherVerbose;
			this.weather = weather;
			this.date = date;			
		}

		public int getTmin() {
			return tmin;
		}

		public int getTmax() {
			return tmax;
		}

		public int getProbarain() {
			return probarain;
		}

		public String getWeatherVerbose() {
			return weatherVerbose;
		}

		public int getWeather() {
			return weather;
		}

		public Date getDate() {
			return date;
		}
		
		
	}
}
