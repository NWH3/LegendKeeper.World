package nwh.legendkeeper.world.map;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/** 
 * Class used to contain all prototypes for
 * modifying and accessing Legend Keeper World Map
 * data from the MongoDB datasource
 * 
 * @author Nathanial.W.Heard
 *
 */
@Repository
public interface WorldMapRepository extends MongoRepository<WorldMap, String> {
	
	WorldMap findOneById(String id);

}
