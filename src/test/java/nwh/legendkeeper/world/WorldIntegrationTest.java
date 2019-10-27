package nwh.legendkeeper.world;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class WorldIntegrationTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WorldIntegrationTest.class);

	private TestRestTemplate restTemplate = new TestRestTemplate();
	
	@Autowired
	private WorldRepository worldRepository;
	
	@LocalServerPort
	private int port;
	
	private HttpHeaders headers = new HttpHeaders();
	
	private World foundWorld;
	
	private final ParameterizedTypeReference<PageImpl<WorldListDto>> worldListDtoType = new ParameterizedTypeReference<PageImpl<WorldListDto>>() {};
	
	@Before
	public void setUp() {
		Page<World> pagedWorld = worldRepository.findAll(PageRequest.of(0, 1));
		if (pagedWorld == null || !pagedWorld.hasContent()) {
			LOGGER.error("No Worlds found in database. Please insert data to run tests or skip.");
		}
		foundWorld = pagedWorld.getContent().get(0);
	}
	
	@Test
	public void testGetAllWorlds_integration_valid() throws Exception {
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<PageImpl<WorldListDto>> response = restTemplate.exchange(
				"http://localhost:" + port + "/world/worlds?page=0&size=20",
				HttpMethod.GET, requestEntity, worldListDtoType);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getContent().get(0).getId()).isEqualTo(foundWorld.getId());
		assertThat(response.getBody().getContent().size()).isGreaterThan(0);
	}
	
	@Test
	public void testGetWorldById_integration_valid() throws Exception {
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<World> response = restTemplate.exchange(
				"http://localhost:" + port + "/world/world/id/" + this.foundWorld.getId(),
				HttpMethod.GET, requestEntity, World.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getId()).isEqualTo(foundWorld.getId());
		assertThat(response.getBody().getName()).isEqualTo(foundWorld.getName());
	}
	
	@Test
	public void testGetWorldById_integration_invalid() throws Exception {
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<World> response = restTemplate.exchange(
				"http://localhost:" + port + "/world/world/id/invalidIdHere",
				HttpMethod.GET, requestEntity, World.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNull();
	}
}
