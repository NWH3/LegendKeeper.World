package nwh.legendkeeper.world;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class WorldServiceTest {

	@Autowired
	private WorldService worldService;

	@MockBean
	private WorldRepository worldRepository;

	/*
	 * Unit tests for World service beyond this point
	 */

	@Test
	public void testGetAllWorlds_unitTest_foundWorlds() throws Exception {

		World world = new World();
		world.setId("1");
		world.setName("World 1");
		
		List<World> worlds = new ArrayList<World>();
		worlds.add(world);
		Page<World> worldPages = new PageImpl<World>(worlds, PageRequest.of(0, 10), 1);

		when(worldRepository.findAll(any(Pageable.class))).thenReturn(worldPages);

		Page<WorldListDto> resposneDto = worldService.getAllWorlds(PageRequest.of(0, 10));

		assertThat(resposneDto.getContent().get(0).getId()).isEqualTo(world.getId());
		assertThat(resposneDto.getContent().get(0).getName()).isEqualTo(world.getName());
	}
	
	@Test
	public void testGetAllWorlds_unitTest_noFoundWorlds() throws Exception {
		List<World> worlds = new ArrayList<World>();
		Page<World> worldPages = new PageImpl<World>(worlds, PageRequest.of(0, 10), 1);

		when(worldRepository.findAll(any(Pageable.class))).thenReturn(worldPages);

		Page<WorldListDto> resposneDto = worldService.getAllWorlds(PageRequest.of(0, 10));

		assertThat(resposneDto.getContent().size()).isEqualTo(0);
	}
	
	@Test
	public void testGetWorldById_unitTest_foundWorld() throws Exception {

		World world = new World();
		world.setId("1");
		world.setName("World 1");

		when(worldRepository.findOneById(any(String.class))).thenReturn(world);

		World resposneDto = worldService.getWorldById("1");

		assertThat(resposneDto.getId()).isEqualTo(world.getId());
		assertThat(resposneDto.getName()).isEqualTo(world.getName());
	}
	
	@Test
	public void testGetWorldById_unitTest_noWorldFound() throws Exception {

		when(worldRepository.findOneById(any(String.class))).thenReturn(null);

		World resposneDto = worldService.getWorldById("1");

		assertThat(resposneDto).isNull();
	}
	
	
}
