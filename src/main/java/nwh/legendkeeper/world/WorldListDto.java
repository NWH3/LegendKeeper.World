package nwh.legendkeeper.world;

/**
 * Class used to contain all data 
 * for a Legend Keeper World including
 * map, name, sessions, etc.
 * 
 * @author Nathanail.W.Heard
 *
 */
public class WorldListDto {
	
	private String name;
	
	private String desc;
	
	private String mapData;
	
	public WorldListDto() {
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

}
