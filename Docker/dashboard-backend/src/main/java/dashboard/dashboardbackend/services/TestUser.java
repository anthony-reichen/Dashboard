package dashboard.dashboardbackend.services;

import dashboard.dashboardbackend.models.User;
import dashboard.dashboardbackend.models.widgets.WeatherWidget;
import dashboard.dashboardbackend.models.widgets.Widget;

public class TestUser {
	
	public static void main(String[] args) {
		WeatherWidget w = new WeatherWidget("2A004");
		User pascal = new User();
		
		pascal.addWidget(w);
		System.out.println(pascal.getWidgets());
		for(Widget x : pascal.getWidgets()) {
			System.out.println(x.getType());
			System.out.println(x.getClass());
		}
	}
}
