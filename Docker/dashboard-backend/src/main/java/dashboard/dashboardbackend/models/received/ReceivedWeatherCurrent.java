package dashboard.dashboardbackend.models.received;

import java.util.ArrayList;
import java.util.Date;

import dashboard.dashboardbackend.models.WeatherReport;


public class ReceivedWeatherCurrent {

	private City city;
	private String update;
	private ArrayList<Forecast> forecast;
	
	public ReceivedWeatherCurrent() {
		
	}
	
	private class Forecast{
		
        private String insee;
        private int cp;
        private double latitude;
        private double longitude;
        private Date datetime;
        private int temp2m;
        private int rh2m;
        private int wind10m;
        private int gust10m;
        private int dirwind10m;
        private double rr10;
        private double rr1;
        private int probarain;
        private int weather;
        private int probafrost;
        private int probafog;
        private int probawind70;
        private int probawind100;
        private int tsoil1;
        private int tsoil2;
        private int gustx;
        private int iso0;
	}
	
	private class City{
		private String insee;
		private int cp;
		private String name;
		private double latitude;
		private double longitude;
		private int altitude;
		
	}

	public WeatherReport toReport() {
		return new WeatherReport(this.city.name, this.forecast.get(0).temp2m, this.forecast.get(0).weather, this.forecast.get(0).wind10m, this.forecast.get(0).dirwind10m);
	}

	public String getInseeCode() {
		return city.insee;
	}

	public City getCity() {
		return city;
	}

	public String getUpdate() {
		return update;
	}

	public ArrayList<Forecast> getForecast() {
		return forecast;
	}
	
	
	
	
}
