package nwh.legendkeeper.world.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import nwh.legendkeeper.world.World;
import nwh.legendkeeper.world.session.CreateWorldSessionRequest;

/**
 * Class used to expose web APIs for 
 * Legend Keeper World Session data
 * 
 * @author Nathanial.W.Heard
 *
 */
@RestController
public class WorldSessionController {
	
	@Autowired
	private WorldSessionService worldSessionService;
	
	/**
	 * Method used to save new session to 
	 * a given world in the database
	 * 
	 * @param worldSessionRequest containing the name and description of the world session
	 * @return the world with the new session
	 */
	@CrossOrigin
	@PostMapping("/create/session")
    public ResponseEntity<World> createWorldSession(@RequestBody CreateWorldSessionRequest worldSessionRequest) {
		World responseDto = worldSessionService.createWorldSession(worldSessionRequest);
		return new ResponseEntity<World>(responseDto, HttpStatus.OK);
    }
	
	/**
	 * Method used to save session to 
	 * a given world in the database
	 * 
	 * @param worldSessionRequest containing the name and description of the world session
	 * @return the world with the updated session
	 */
	@CrossOrigin
	@PutMapping("/update/session")
    public ResponseEntity<World> updateWorldSession(@RequestBody UpdateWorldSessionRequest worldSessionRequest) {
		World responseDto = worldSessionService.updateWorldSession(worldSessionRequest);
		return new ResponseEntity<World>(responseDto, HttpStatus.OK);
    }

	/**
	 * Method used to delete a session from 
	 * a given world in the database
	 * 
	 * @param worldId of the world to update
	 * @param sessionId of the session to delete
	 * @return the world with the removed session list
	 */
	@CrossOrigin
	@DeleteMapping("/delete/world/{worldId}/session/{sessionId}")
    public ResponseEntity<World> deleteWorldSession(@PathVariable("worldId") String worldId, @PathVariable("sessionId") String sessionId) {
		World responseDto = worldSessionService.deleteWorldSession(worldId, sessionId);
		return new ResponseEntity<World>(responseDto, HttpStatus.OK);
    }
}
