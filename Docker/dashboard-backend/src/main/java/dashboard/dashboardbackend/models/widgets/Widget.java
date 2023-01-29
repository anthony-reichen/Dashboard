package dashboard.dashboardbackend.models.widgets;

import java.util.UUID;

import org.springframework.data.annotation.Id;

public abstract class Widget {

	

	private String id;
	private String type;
	
	public Widget() {
		this.id = UUID.randomUUID().toString();
	}
	
	public Widget(String type) {
		this.id = UUID.randomUUID().toString();
		this.type = type;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}
	
	
}
