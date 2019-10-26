package nwh.legendkeeper.world;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import nwh.legendkeeper.world.WorldService;

/**
 * Class used to expose web APIs for 
 * Legend Keeper World data
 * 
 * @author Nathanial.W.Heard
 *
 */
@RestController
public class WorldController {
	
	@Autowired
	private WorldService worldService;
	
	/**
	 * Method used to return all worlds for a given user
	 * 
	 * @return
	 */
	@CrossOrigin
	@GetMapping("/worlds")
    public ResponseEntity<Page<WorldListDto>> getAllWorlds(Pageable pageable) {
		Page<WorldListDto> responseDto = worldService.getAllWorlds(pageable);
		return new ResponseEntity<Page<WorldListDto>>(responseDto, HttpStatus.OK);
    }

}
