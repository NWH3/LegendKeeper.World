package nwh.legendkeeper.world.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import nwh.legendkeeper.world.World;
import nwh.legendkeeper.world.map.CreateWorldMapRequest;
import nwh.legendkeeper.world.map.WorldMap;

/**
 * Class used to expose web APIs for 
 * Legend Keeper World Map data
 * 
 * @author Nathanial.W.Heard
 *
 */
@RestController
public class WorldMapController {
	
	@Autowired
	private WorldMapService worldMapService;
	
	/**
	 * Method used to return a single specified
	 * world map for a given user
	 * 
	 * @return a single World Map Dto with all info
	 */
	@CrossOrigin
	@GetMapping("/map/id/{id}")
    public ResponseEntity<WorldMap> getWorldMapById(@PathVariable("id") String id) {
		WorldMap responseDto = worldMapService.getWorldMapById(id);
		return new ResponseEntity<WorldMap>(responseDto, HttpStatus.OK);
    }
	
	/**
	 * Method used to save a given map to
	 * a specified world on the database
	 * 
	 * @param worldMapRequest containing the name, world id, and map data for a new map
	 * @return the created world with new maps list
	 */
	@CrossOrigin
	@PutMapping("/create/map")
    public ResponseEntity<World> createWorldMap(@RequestBody CreateWorldMapRequest worldMapRequest) {
		World responseDto = worldMapService.createWorldMap(worldMapRequest);
		return new ResponseEntity<World>(responseDto, HttpStatus.OK);
    }
	
	/**
	 * Method used to update a given map to
	 * a specified world on the database
	 * 
	 * @param worldMapRequest containing the name, world id, and map data for a new map
	 * @return the created world with new maps list
	 */
	@CrossOrigin
	@PutMapping("/update/map")
    public ResponseEntity<World> updateWorldMap(@RequestBody UpdateWorldMapRequest worldMapRequest) {
		World responseDto = worldMapService.updateWorldMap(worldMapRequest);
		return new ResponseEntity<World>(responseDto, HttpStatus.OK);
    }
	
	/**
	 * Method used to delete a given map to
	 * a specified world on the database
	 * 
	 * @param worldId being the world associated to the map to update
	 * @param mapId being the id of the map to delete
	 * @return the updated world with new maps list
	 */
	@CrossOrigin
	@DeleteMapping("/delete/world/{worldId}/map/{mapId}")
    public ResponseEntity<World> deleteWorldMap(@PathVariable("worldId") String worldId, @PathVariable("mapId") String mapId) {
		World responseDto = worldMapService.deleteWorldMap(worldId, mapId);
		return new ResponseEntity<World>(responseDto, HttpStatus.OK);
    }
}
