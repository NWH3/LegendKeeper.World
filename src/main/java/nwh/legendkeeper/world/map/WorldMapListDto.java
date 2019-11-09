package nwh.legendkeeper.world.map;

/**
 * Class used to contain 
 * both needed fields for world
 * map listing 
 * 
 * @author Nathanial.W.Heard
 *
 */
public class WorldMapListDto {
	
	private String id;
	
	private String name;
	
	public WorldMapListDto() {
		// Default
	}
	
	public WorldMapListDto(String name, String id) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
