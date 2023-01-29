package dashboard.dashboardbackend.models;

import java.util.Date;

public class Forecast {

    private String insee;
    private int cp;
    private double latitude;
    private double longitude;
    private int day;
    private Date datetime;
    private int wind10m;
    private int gust10m;
    private int dirwind10m;
    private double rr10;
    private double rr1;
    private int probarain;
    private int weather;
    private int tmin;
    private int tmax;
    private int sun_hours;
    private int etp;
    private int probafrost;
    private int probafog;
    private int probawind70;
    private int probawind100;
    private int gustx;
    
	public Forecast(String insee, int cp, double latitude, double longitude, int day, Date datetime, int wind10m,
			int gust10m, int dirwind10m, double rr10, double rr1, int probarain, int weather, int tmin, int tmax,
			int sun_hours, int etp, int probafrost, int probafog, int probawind70, int probawind100, int gustx) {
		super();
		this.insee = insee;
		this.cp = cp;
		this.latitude = latitude;
		this.longitude = longitude;
		this.day = day;
		this.datetime = datetime;
		this.wind10m = wind10m;
		this.gust10m = gust10m;
		this.dirwind10m = dirwind10m;
		this.rr10 = rr10;
		this.rr1 = rr1;
		this.probarain = probarain;
		this.weather = weather;
		this.tmin = tmin;
		this.tmax = tmax;
		this.sun_hours = sun_hours;
		this.etp = etp;
		this.probafrost = probafrost;
		this.probafog = probafog;
		this.probawind70 = probawind70;
		this.probawind100 = probawind100;
		this.gustx = gustx;
	}

	public String getInsee() {
		return insee;
	}

	public int getCp() {
		return cp;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public int getDay() {
		return day;
	}

	public Date getDatetime() {
		return datetime;
	}

	public int getWind10m() {
		return wind10m;
	}

	public int getGust10m() {
		return gust10m;
	}

	public int getDirwind10m() {
		return dirwind10m;
	}

	public double getRr10() {
		return rr10;
	}

	public double getRr1() {
		return rr1;
	}

	public int getProbarain() {
		return probarain;
	}

	public int getWeather() {
		return weather;
	}

	public int getTmin() {
		return tmin;
	}

	public int getTmax() {
		return tmax;
	}

	public int getSun_hours() {
		return sun_hours;
	}

	public int getEtp() {
		return etp;
	}

	public int getProbafrost() {
		return probafrost;
	}

	public int getProbafog() {
		return probafog;
	}

	public int getProbawind70() {
		return probawind70;
	}

	public int getProbawind100() {
		return probawind100;
	}

	public int getGustx() {
		return gustx;
	}
	
	
}
