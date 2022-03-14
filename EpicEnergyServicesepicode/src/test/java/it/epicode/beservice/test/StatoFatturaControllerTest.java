package it.epicode.beservice.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import it.epicode.beservice.model.StatoFattura;
import it.epicode.beservice.service.StatoFatturaService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class StatoFatturaControllerTest {

	@LocalServerPort
	private int port = 8080;
	
	@Autowired
	TestRestTemplate restTemplate;
	
	@Autowired
	StatoFatturaService statoFatturaService;
	
	@Test
	void testEliminaStatoFattura() {
		StatoFattura sfTest = new StatoFattura("Stato Fattura Test");
		statoFatturaService.salvaStatoFattura(sfTest);
		assertTrue(statoFatturaService.existsByNome("Stato Fattura Test"));
		statoFatturaService.eliminaStatoFattura(sfTest.getId());
		assertFalse(statoFatturaService.existsByNome("Stato Fattura Test"));
	}

}
