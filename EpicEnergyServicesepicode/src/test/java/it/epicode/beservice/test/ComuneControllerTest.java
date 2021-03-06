package it.epicode.beservice.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ComuneControllerTest {

	@LocalServerPort
	private int port = 8080;
	
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void testGetComuneByNome() {
		assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/comunecontroller/getcomunebynome?nome=Cuneo", String.class)).contains("Cuneo");
	}

}
