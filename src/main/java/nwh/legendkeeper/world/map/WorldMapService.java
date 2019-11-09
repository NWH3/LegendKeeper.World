package nwh.legendkeeper.world.map;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nwh.legendkeeper.world.World;
import nwh.legendkeeper.world.WorldRepository;
import nwh.legendkeeper.world.map.WorldMap;
import nwh.legendkeeper.world.map.WorldMapListDto;
import nwh.legendkeeper.world.map.WorldMapRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * Class used to contain all logic for processing 
 * Legend Keeper World data for access
 * 
 * @author Nathanial.W.Heard
 *
 */
@Service
public class WorldMapService {

	private static final Logger LOGGER = LoggerFactory.getLogger(WorldMapService.class);
	
	@Autowired
	private WorldRepository worldRepo;
	
	@Autowired
	private WorldMapRepository worldMapRepo;
	
	public WorldMapService() {
		// Default
	}
	
	public WorldMap getWorldMapById(String id) {
		WorldMap worldMap = worldMapRepo.findOneById(id);
		if (worldMap != null) {
			LOGGER.info("World Map found with record id: " + worldMap.getId());
		} else {
			LOGGER.warn("World Map not found with given id: " + id);
		}
		return worldMap;
	}
	
	public World updateWorldMap(UpdateWorldMapRequest worldRequest) {
		World world = worldRepo.findOneById(worldRequest.getWorldMapId());
		if (world != null) {
			world.getMaps().stream().forEach(map -> {
				if (map.getId().equals(worldRequest.getWorldMapId())) {
					map.setName(worldRequest.getName());
				}
			});
			world.setDateUpdated(new Date().toString());
			world = worldRepo.save(world);
			WorldMap worldMap = worldMapRepo.findOneById(worldRequest.getWorldMapId());
			if (worldMap != null) {
				worldMap.setMap(worldRequest.getMapData());
				worldMap.setDateUpdated(new Date().toString());
				worldMapRepo.save(worldMap);
			} else {
				LOGGER.error("Unable to update world map with id: " + worldRequest.getWorldMapId());
			}
		} else {
			LOGGER.error("Unable to update world with id: " + worldRequest.getWorldId());
		}
		return world;
	}

	public World createWorldMap(CreateWorldMapRequest worldMapRequest) {
		World world = worldRepo.findOneById(worldMapRequest.getWorldId());
		if (world != null) {
			LOGGER.info("World found with record id: " + world.getId());
			String linkedMapId = null;
			for (WorldMapListDto worldMapListDto : world.getMaps()) {
				if (worldMapListDto != null && worldMapListDto.getName().equals(worldMapRequest.getName())) {
					linkedMapId = worldMapListDto.getId();
					break;
				}
			}
			WorldMap createdWorldMap =  worldMapRepo.findOneById(linkedMapId);
			if (createdWorldMap == null) {
				createdWorldMap = worldMapRepo.save(new WorldMap(worldMapRequest.getMapData()));
			} else {
				createdWorldMap.setMap(worldMapRequest.getMapData());
				createdWorldMap.setDateUpdated(new Date().toString());
				createdWorldMap = worldMapRepo.save(createdWorldMap);
			}
			world.getMaps().add(new WorldMapListDto(worldMapRequest.getName(), createdWorldMap.getId()));
			world = worldRepo.save(world);
		} else {
			LOGGER.warn("Unable to save new world map as world was not found with given world id: " + worldMapRequest.getWorldId());
		}
		return world;
	}

	public World deleteWorldMap(String worldId, String mapId) {
		World world = worldRepo.findOneById(worldId);
		if (world != null) {
			worldRepo.deleteById(mapId);
			
			List<WorldMapListDto> refinedMaps = new ArrayList<WorldMapListDto>();
			world.getMaps().stream().forEach(map -> {
				LOGGER.error(map.getId());
				if (!map.getId().equals(mapId)) {
					refinedMaps.add(map);
				}
			});
			world.setMaps(refinedMaps);
			
			world = worldRepo.save(world);
		} else {
			LOGGER.error("Unable to find world with id: " + worldId + " and map id: " + mapId);
		}
		return world;
	}

}
