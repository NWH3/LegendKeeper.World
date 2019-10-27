package nwh.legendkeeper.world;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


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
public class WorldService {

	private static final Logger LOGGER = LoggerFactory.getLogger(WorldService.class);
	
	@Autowired
	private WorldRepository worldRepo;

	public Page<WorldListDto> getAllWorlds(Pageable pageable) {
		Page<World> worlds = worldRepo.findAll(pageable);
		Page<WorldListDto> worldDtos = null;
		if (worlds != null && !worlds.isEmpty()) {
		 worldDtos = new PageImpl<>(worlds.stream()
				.map(world -> new WorldListDto(world))
				.collect(Collectors.toList()), pageable, worlds.getTotalElements());
		}
		LOGGER.info("Worlds returned: " + worldDtos);
		return worldDtos;
	}
	
	public World getWorldById(String id) {
		World world = worldRepo.findOneById(id);
		LOGGER.info("World found with name: " + world.getName() + " and id: " + world.getId());
		return world;
	}

}
