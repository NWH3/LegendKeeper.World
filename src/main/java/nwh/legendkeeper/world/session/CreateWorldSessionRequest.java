package nwh.legendkeeper.world.session;

import javax.validation.constraints.NotNull;

/**
 * Class used to contain all data for a 
 * given world session 
 * 
 * @author Nathanial.W.Heard
 *
 */
public class CreateWorldSessionRequest {
	
	@NotNull
	private String name;
	
	private String desc;

	@NotNull	
	private String worldId;
	
	public CreateWorldSessionRequest() {
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

	public String getWorldId() {
		return worldId;
	}

	public void setWorldId(String worldId) {
		this.worldId = worldId;
	}

}
