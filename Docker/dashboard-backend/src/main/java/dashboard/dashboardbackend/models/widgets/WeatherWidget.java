package dashboard.dashboardbackend.models.widgets;

public class WeatherWidget extends Widget{
	
	private String insee;
	private tempUnit temp = tempUnit.CELSIUS;
	public enum  tempUnit {CELSIUS, FRHNT};

	public WeatherWidget() {
		
	}
	public WeatherWidget(String insee) {
		super("weather");
		this.insee = insee;
	}
	
	public WeatherWidget(String insee, WeatherWidget.tempUnit tempUnit) {
		super("weather");
		this.insee = insee;
		this.temp = tempUnit;
	}

	public String getInsee() {
		return insee;
	}
	
	public void setInsee(String insee) {
		this.insee = insee;
	}

	public tempUnit getTemp() {
		return temp;
	}

	public void setTemp(tempUnit temp) {
		this.temp = temp;
	}
	
	
}
