package nwh.legendkeeper.world;

import javax.validation.constraints.NotNull;

public class CreateWorldRequest {
	
	@NotNull
	private String name;
	
	@NotNull
	private String era;
	
	private String desc;
	
	public CreateWorldRequest() {
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
}
