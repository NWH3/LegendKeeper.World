package nwh.legendkeeper.world;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

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
public class WorldService {

	private static final Logger LOGGER = LoggerFactory.getLogger(WorldService.class);
	
	private Gson gson;
	
	@Autowired
	private WorldRepository worldRepo;
	
	@Autowired
	private WorldMapRepository worldMapRepo;
	
	public WorldService() {
		this.gson = new Gson();
	}

	public Page<WorldListDto> getAllWorlds(Pageable pageable) {
		Page<World> worlds = worldRepo.findAll(pageable);
		Page<WorldListDto> worldDtos = new PageImpl<>(new ArrayList<>(), pageable, 0);
		if (worlds != null && !worlds.isEmpty()) {
			worldDtos = new PageImpl<>(worlds.stream()
				.map(world -> new WorldListDto(world))
				.collect(Collectors.toList()), pageable, worlds.getTotalElements());
			LOGGER.info("Worlds returned: " + gson.toJson(worldDtos));
		}
		return worldDtos;
	}
	
	public World getWorldById(String id) {
		World world = worldRepo.findOneById(id);
		if (world != null) {
			LOGGER.info("World found with name: " + world.getName() + " and id: " + world.getId());
		} else {
			LOGGER.warn("World not found with given id: " + id);
		}
		return world;
	}

	public World updateWorld(UpdateWorldRequest worldRequest) {
		World world = worldRepo.findOneById(worldRequest.getId());
		if (world != null) {
			world.setName(worldRequest.getName());
			world.setEra(worldRequest.getEra());
			world.setDesc(worldRequest.getDesc());
			world.setDateUpdated(new Date().toString());
			world = worldRepo.save(world);
		} else {
			LOGGER.error("Unable to update world with id: " + worldRequest.getId());
		}
		return world;
	}

	public World createWorld(CreateWorldRequest worldRequest) {
		World world = new World(worldRequest);
		world = worldRepo.save(world);
		return world;
	}
	
	public World deleteWorld(String worldId) {
		World world = worldRepo.findOneById(worldId);
		if (world != null) {
			worldRepo.deleteById(worldId);
		} else {
			LOGGER.error("Unable to find world with id: " + worldId);
		}
		return world;
	}
	
	public void initMongoDB() {
		gson = new Gson();
		Page<World> worlds = worldRepo.findAll(PageRequest.of(0, 10));
		if (worlds.isEmpty()) {
			// Insert Symbaroum world one
			String symbaroumdWorldOne = readFileAsObject("db/migration/Symbaroum_World_V0.0.1_09.13.2019.json");
			String symbaroumWorldMapOne = readFileAsObject("db/migration/Symbaroum_World_Map_V0.0.1_09.13.2019.json");

			WorldMap worldMap = new WorldMap(symbaroumWorldMapOne);
			worldMap = worldMapRepo.save(worldMap);
			World world = gson.fromJson(symbaroumdWorldOne, World.class);
			world.getSessions().stream().forEach(session -> {
				session.setId(UUID.randomUUID().toString());
			});
			List<WorldMapListDto> mapDtos = world.getMaps();
			mapDtos.add(new WorldMapListDto(world.getName(), worldMap.getId()));
			world.setMaps(mapDtos);
			world = worldRepo.save(world);

			// Insert Symbaroum world two
			String symbaroumdWorldTwo = readFileAsObject("db/migration/Symbaroum_World_V0.0.1_09.14.2019.json");
			String symbaroumWorldMapTwo = readFileAsObject("db/migration/Symbaroum_World_Map_V0.0.1_09.14.2019.json");

			WorldMap worldMapTwo = new WorldMap(symbaroumWorldMapTwo);
			worldMapTwo = worldMapRepo.save(worldMapTwo);
			World worldTwo = gson.fromJson(symbaroumdWorldTwo, World.class);
			worldTwo.getSessions().stream().forEach(session -> {
				session.setId(UUID.randomUUID().toString());
			});
			List<WorldMapListDto> mapDtosTwo = worldTwo.getMaps();
			mapDtosTwo.add(new WorldMapListDto(worldTwo.getName(), worldMapTwo.getId()));
			worldTwo.setMaps(mapDtosTwo);
			worldRepo.save(worldTwo);
		}
	}
	
	private String readFileAsObject(String worldFilePath) {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(worldFilePath);
		InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
		BufferedReader reader = new BufferedReader(streamReader);
		StringBuilder strRead = new StringBuilder();
		try {
			for (String line; (line = reader.readLine()) != null;) {
				strRead.append(line);
			}
		} catch (IOException e) {
			LOGGER.error("Unable to read data from file: " + worldFilePath + " with exception: " + e.getMessage());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				LOGGER.error("To close input stream for file: " + worldFilePath + " with exception: " + e.getMessage());
			}
		}
		return strRead.toString();
	}

}
