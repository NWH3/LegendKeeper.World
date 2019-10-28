package nwh.legendkeeper.world;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,classes = WorldApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class WorldIntegrationTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WorldIntegrationTest.class);

	private TestRestTemplate restTemplate = new TestRestTemplate();
	
	@Autowired
	private WorldRepository worldRepository;
	
	@Autowired
	private WorldMapRepository worldMapRepository;
	
	@LocalServerPort
	private int port;
	
	private HttpHeaders headers = new HttpHeaders();
	
	private World foundWorld;
	
	private WorldMap foundWorldMap;
	
	private final ParameterizedTypeReference<ResponsePageImpl<WorldListDto>> worldListDtoType = new ParameterizedTypeReference<ResponsePageImpl<WorldListDto>>() {};
	
	@Test
	public void testGetAllWorlds_integration_valid() throws Exception {
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<ResponsePageImpl<WorldListDto>> response = restTemplate.exchange(
				"http://localhost:" + port + "/world/worlds?page=0&size=20",
				HttpMethod.GET, requestEntity, worldListDtoType);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getContent().size()).isGreaterThan(0);
		assertThat(response.getBody().getContent().get(0).getId()).isNotNull();
		assertThat(response.getBody().getContent().get(0).getName()).isNotNull();
	}
	
	@Test
	public void testGetWorldById_integration_valid() throws Exception {
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<World> response = restTemplate.exchange(
				"http://localhost:" + port + "/world/id/" + this.getFirstWorld().getId(),
				HttpMethod.GET, requestEntity, World.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getId()).isEqualTo(foundWorld.getId());
		assertThat(response.getBody().getName()).isEqualTo(foundWorld.getName());
	}
	
	@Test
	public void testGetWorldById_integration_invalid() throws Exception {
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<World> response = restTemplate.exchange(
				"http://localhost:" + port + "/world/id/invalidIdHere",
				HttpMethod.GET, requestEntity, World.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNull();
	}
	
	@Test
	public void testGetWorldMapById_integration_valid() throws Exception {
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<World> response = restTemplate.exchange(
				"http://localhost:" + port + "/world/map/id/" + this.getFirstWorldMap().getId(),
				HttpMethod.GET, requestEntity, World.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getId()).isEqualTo(foundWorldMap.getId());
	}
	
	private World getFirstWorld() {
		if (foundWorld == null) {
			Page<World> pagedWorld = worldRepository.findAll(PageRequest.of(0, 1));
			if (pagedWorld == null || !pagedWorld.hasContent()) {
				LOGGER.error("No Worlds found in database. Please insert data to run tests or skip.");
			}
			foundWorld = pagedWorld.getContent().get(0);
		}
		return foundWorld;
	}
	
	private WorldMap getFirstWorldMap() {
		if (foundWorldMap == null) {
			Page<WorldMap> pagedWorldMap = worldMapRepository.findAll(PageRequest.of(0, 1));
			if (pagedWorldMap == null || !pagedWorldMap.hasContent()) {
				LOGGER.error("No World Maps found in database. Please insert data to run tests or skip.");
			}
			foundWorldMap = pagedWorldMap.getContent().get(0);
		}
		return foundWorldMap;
	}
}
