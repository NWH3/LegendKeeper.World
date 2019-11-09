package nwh.legendkeeper.world.session;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Class used to contain all data for a 
 * given world session to update including 
 * id, name, desc, etc.
 * 
 * @author Nathanial.W.Heard
 *
 */
public class UpdateWorldSessionRequest {
	
	@Id
	private String id;
	
	@NotNull
	private String name;
	
	private String desc;

	@NotNull	
	private String worldId;
	
	public UpdateWorldSessionRequest() {
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
