package nwh.legendkeeper.world;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import nwh.legendkeeper.world.map.WorldMapListDto;
import nwh.legendkeeper.world.session.WorldSession;

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
	
	private List<WorldSession> sessions;
	
	private List<WorldMapListDto> maps;
	
	private String dateCreated;
	
	private String dateUpdated;
	
	public World() {
		// Default
		this.maps = new ArrayList<WorldMapListDto>();
		this.sessions = new ArrayList<WorldSession>();
		this.dateCreated = new Date().toString();
		this.dateUpdated = new Date().toString();
	}

	public World(CreateWorldRequest worldRequest) {
		this.name = worldRequest.getName();
		this.era = worldRequest.getEra();
		this.desc = worldRequest.getDesc();
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

	public List<WorldSession> getSessions() {
		return sessions;
	}

	public void setSessions(List<WorldSession> sessions) {
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

	public List<WorldMapListDto> getMaps() {
		return maps;
	}

	public void setMaps(List<WorldMapListDto> mapIds) {
		this.maps = mapIds;
	}

}
