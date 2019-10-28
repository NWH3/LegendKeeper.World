package nwh.legendkeeper.world;

import java.util.Date;

/**
 * Class used to contain all 
 * Legend Keeper World Session data 
 * including name, desc, date, etc.
 * 
 * @author Nathanial.W.Heard
 *
 */
public class Session {
	
	private String name;

	private String desc;
	
	private String dateScheduled;
	
	private String dateCreated;
	
	private String dateUpdated;
	
	public Session() {
		// Default
		this.dateCreated = new Date().toString();
		this.dateUpdated = new Date().toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDateScheduled() {
		return dateScheduled;
	}

	public void setDateScheduled(String dateScheduled) {
		this.dateScheduled = dateScheduled;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
}
