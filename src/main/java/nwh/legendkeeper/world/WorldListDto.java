package nwh.legendkeeper.world;

/**
 * Class used to contain a shortened 
 * set of data for a Legend Keeper World including
 * name, desc, etc.
 * 
 * @author Nathanail.W.Heard
 *
 */
public class WorldListDto {
	
	private String id;
	
	private String name;
	
	private String era;
	
	private String desc;
	
	public WorldListDto() {
		// Default
	}
	
	public WorldListDto(World world) {
		this.id = world.getId();
		this.name = world.getName();
		this.era = world.getEra();
		this.desc = world.getDesc();
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
}
