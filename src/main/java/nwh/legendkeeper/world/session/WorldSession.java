package nwh.legendkeeper.world.session;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Id;

import com.google.gson.annotations.SerializedName;

/**
 * Class used to contain all 
 * Legend Keeper World Session data 
 * including name, desc, date, etc.
 * 
 * @author Nathanial.W.Heard
 *
 */
public class WorldSession {
	
	@Id
	@SerializedName("_id")
	private String id;
	
	private String name;

	private String desc;
	
	private String dateScheduled;
	
	private String dateCreated;
	
	private String dateUpdated;
	
	public WorldSession() {
		// Default
		this.dateCreated = new Date().toString();
		this.dateUpdated = new Date().toString();
	}

	public WorldSession(CreateWorldSessionRequest createSessionRequest) {
		this.id = UUID.randomUUID().toString();
		this.name = createSessionRequest.getName();
		this.desc = createSessionRequest.getDesc();
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
