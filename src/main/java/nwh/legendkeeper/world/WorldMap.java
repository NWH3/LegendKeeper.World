package nwh.legendkeeper.world;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class used to contain all data 
 * for a Legend Keeper World map including
 * map data, name, created date, etc.
 * 
 * @author Nathanail.W.Heard
 *
 */
@Document(collection = "worldmap")
public class WorldMap {
	
	@Id
	private String id;
	
	private String map;
	
	private String name;
	
	private String dateCreated;
	
	private String dateUpdated;
	
	public WorldMap() {
		// Default
		this.dateCreated = new Date().toString();
		this.dateUpdated = new Date().toString();
	}

	public WorldMap(String map) {
		this.map = map;
		this.dateCreated = new Date().toString();
		this.dateUpdated = new Date().toString();
	}
	
	public WorldMap(String map, String name) {
		this.map = map;
		this.name  = name;
		this.dateCreated = new Date().toString();
		this.dateUpdated = new Date().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
