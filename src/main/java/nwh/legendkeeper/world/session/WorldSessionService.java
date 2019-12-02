package nwh.legendkeeper.world.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nwh.legendkeeper.world.World;
import nwh.legendkeeper.world.WorldRepository;
import nwh.legendkeeper.world.session.CreateWorldSessionRequest;
import nwh.legendkeeper.world.session.WorldSession;

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
public class WorldSessionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(WorldSessionService.class);
	
	@Autowired
	private WorldRepository worldRepo;
	
	public WorldSessionService() {
		// Default
	}

	public World updateWorldSession(UpdateWorldSessionRequest worldSessionRequest) {
		World world = worldRepo.findOneById(worldSessionRequest.getWorldId());
		if (world != null) {
			List<WorldSession> refinedSessions = new ArrayList<WorldSession>();
			world.getSessions().stream().forEach(session -> {
				if (session.getId().equals(worldSessionRequest.getId())) {
					session.setName(worldSessionRequest.getName());
					session.setDesc(worldSessionRequest.getDesc());
					session.setDateUpdated(new Date().toString());	
				}
				refinedSessions.add(session);
			});
			world.setSessions(refinedSessions);
			world = worldRepo.save(world);
		} else {
			LOGGER.error("Unable to find world for new sessions with id: " + worldSessionRequest.getWorldId());
		}
		return world;
	}

	public World createWorldSession(CreateWorldSessionRequest createSessionRequest) {
		World world = worldRepo.findOneById(createSessionRequest.getWorldId());
		if (world != null) {
			WorldSession session = new WorldSession(createSessionRequest);
			world.getSessions().add(session);
			world = worldRepo.save(world);
		} else {
			LOGGER.error("Unable to find world for new sessions with id: " + createSessionRequest.getWorldId());
		}
		return world;
	}

	public World deleteWorldSession(String worldId, String sessionId) {
		World world = worldRepo.findOneById(worldId);
		if (world != null) {
			List<WorldSession> refinedSessions = new ArrayList<WorldSession>();
			world.getSessions().stream().forEach(session -> {
				LOGGER.error( "" + session.getId());
				if (!("" + session.getId()).equals(sessionId)) {
					refinedSessions.add(session);
				}
			});
			world.setSessions(refinedSessions);
			world = worldRepo.save(world);
		} else {
			LOGGER.error("Unable to find world with id: " + worldId + " and session id: " + sessionId);
		}
		return world;
	}
}
