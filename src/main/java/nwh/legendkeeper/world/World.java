package nwh.legendkeeper.world;

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
	
	private String desc;
	
	private List<String> sessions;
	
	private String mapData;
	
	private String dateCreated;
	
	private String dateUpdated;
	
	public World() {
		// Default
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

	public String getMapData() {
		return mapData;
	}

	public void setMapData(String mapData) {
		this.mapData = mapData;
	}

	public List<String> getSessions() {
		return sessions;
	}

	public void setSessions(List<String> sessions) {
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

}
