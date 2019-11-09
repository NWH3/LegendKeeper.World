package nwh.legendkeeper.world.map;

import javax.validation.constraints.NotNull;

/** 
 * Class used to contain all data 
 * for a given update world map
 * request including id, name, map bin data, etc.
 * 
 * @author Nathanial.W.Heard
 *
 */
public class UpdateWorldMapRequest {
	
	@NotNull
	private String worldMapId;
	
	@NotNull
	private String name;
	
	@NotNull
	private String mapData;
	
	@NotNull
	private String worldId;
	
	@NotNull
	private String creator;
	
	public UpdateWorldMapRequest() {
		// Default
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMapData() {
		return mapData;
	}

	public void setMapData(String mapData) {
		this.mapData = mapData;
	}

	public String getWorldId() {
		return worldId;
	}

	public void setWorldId(String worldId) {
		this.worldId = worldId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getWorldMapId() {
		return worldMapId;
	}

	public void setWorldMapId(String worldMapId) {
		this.worldMapId = worldMapId;
	}
}
