package nwh.legendkeeper.world;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/** 
 * Class used to contain all prototypes for
 * modifying and accessing Legend Keeper World
 * data from the MongoDB datasource
 * 
 * @author Nathanial.W.Heard
 *
 */
@Repository
public interface WorldRepository extends MongoRepository<World, String> {

	Page<World> findAll(Pageable pageable);
	
	World findOneById(String id);

}
