package nwh.legendkeeper.world;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class used to contain all data 
 * for a Legend Keeper World including
 * map data, name, sessions, etc.
 * 
 * @author Nathanail.W.Heard
 *
 */
@Document(collection = "world")
public class World {
	
	@Id
	private String id;
	
	private String name;
	
	private String era;
	
	private String desc;
	
	private List<Session> sessions;
	
	private List<String> mapIds;
	
	private String dateCreated;
	
	private String dateUpdated;
	
	public World() {
		// Default
		this.mapIds = new ArrayList<String>();
		this.sessions = new ArrayList<Session>();
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

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
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

	public String getEra() {
		return era;
	}

	public void setEra(String era) {
		this.era = era;
	}

	public List<String> getMapIds() {
		return mapIds;
	}

	public void setMapIds(List<String> mapIds) {
		this.mapIds = mapIds;
	}

}
