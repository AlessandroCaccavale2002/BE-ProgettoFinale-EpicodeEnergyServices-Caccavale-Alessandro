package it.epicode.beservice.test;


import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class IndirizzoControllerTest {

	@LocalServerPort
	private int port = 8080;
	
	@Autowired
	TestRestTemplate restTemplate;
	
	@Test
	void testSalvaIndirizzo() throws JSONException {

		
	}

}



