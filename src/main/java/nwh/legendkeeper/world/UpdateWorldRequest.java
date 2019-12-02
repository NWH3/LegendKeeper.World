package nwh.legendkeeper.world;

import javax.validation.constraints.NotNull;

/**
 * Class used to contain all
 * needed data for a world update
 * request including id, name, desc, etc.
 * 
 * @author Nathanial.W.Heard
 *
 */
public class UpdateWorldRequest {

	@NotNull
	private String id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String era;
	
	private String desc;
	
	public UpdateWorldRequest() {
		// Default
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEra() {
		return era;
	}

	public void setEra(String era) {
		this.era = era;
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
}
