package it.epicode.beservice.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.beservice.model.Fattura;
import it.epicode.beservice.service.ClienteService;
import it.epicode.beservice.service.FatturaService;
import it.epicode.beservice.service.StatoFatturaService;

@RestController
@RequestMapping("/fatturacontroller")
public class FatturaController {

	@Autowired
	FatturaService fatturaService;
	@Autowired
	StatoFatturaService statoFatturaService;
	@Autowired
	ClienteService clienteService;

	@PostMapping("/salvafattura")
	public void salvaFattura(@RequestBody Fattura f) {
		fatturaService.salvaFattura(f);
	}

	@PostMapping("/aggiornafattura/{id}")
	public void aggiornaFattura(@PathVariable(required = true) Long id, @RequestBody Fattura f) {
		fatturaService.updateFattura(id, f);
	}

	@GetMapping("/eliminafattura/{id}")
	public void eliminaFattura(@PathVariable(required = true) Long id) {
		fatturaService.eliminaFattura(id);
	}

	@GetMapping("/getfatturabycliente")
	public List<Fattura> getFatturaByCliente(@RequestParam String ragioneSociale,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		return fatturaService.getFatturaByCliente(ragioneSociale, pag);
	}

	@GetMapping("/getfatturabystatofattura")
	public List<Fattura> getFatturaByStatoFattura(@RequestParam String statoFattura,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		return fatturaService.getFatturaByStatoFattura(statoFattura, pag);
	}

	@GetMapping("/getfatturabydata")
	public List<Fattura> getFatturaByData(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		return fatturaService.getFatturaByData(data, pag);
	}

	@GetMapping("/getfatturabyanno")
	public List<Fattura> getFatturaByAnno(@RequestParam Integer anno, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		return fatturaService.getFatturaByAnno(anno, pag);
	}

	@GetMapping("/getfatturabyrangeimporto")
	public List<Fattura> getFatturaByRangeImporto(@RequestParam(defaultValue = "0") Double importoMin,
			@RequestParam(defaultValue = "10000000000") Double importoMax,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		return fatturaService.getFatturaByRangeImporto(importoMin, importoMax, pag);
	}
	@GetMapping("/salvafatturaform")
	public void salvaFatturaForm(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data, Long numero,
			Integer anno, Double importo, String nomeStatoFattura, String ragioneSocialeCliente) {
		Fattura f = new Fattura();
		f.setData(data);
		f.setNumero(numero);
		f.setAnno(anno);
		f.setImporto(importo);
		f.setStato(statoFatturaService.getByNome(nomeStatoFattura));
		f.setCliente(clienteService.getByRagioneSociale(ragioneSocialeCliente));
		fatturaService.salvaFattura(f);
	}
	
	@GetMapping("/eliminafatturaform")
	public void eliminaFatturaForm(@RequestParam Long id) {
		fatturaService.eliminaFattura(id);
	}
	
	@GetMapping("/getfatturabyclienteform")
	public ModelAndView getFatturaByClienteForm(@RequestParam String ragioneSociale,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "1000") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		ModelAndView model = new ModelAndView();
		List<Fattura> list = fatturaService.getFatturaByCliente(ragioneSociale, pag);
		model.addObject("listaFatture", list);
		model.setViewName("ritornochiamatafatture");
		return model;
	}

	@GetMapping("/getfatturabystatofatturaform")
	public ModelAndView getFatturaByStatoFatturaForm(@RequestParam String statoFattura,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "1000") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		ModelAndView model = new ModelAndView();
		List<Fattura> list = fatturaService.getFatturaByStatoFattura(statoFattura, pag);
		model.addObject("listaFatture", list);
		model.setViewName("ritornochiamatafatture");
		return model;
	}

	@GetMapping("/getfatturabydataform")
	public ModelAndView getFatturaByDataForm(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "1000") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		ModelAndView model = new ModelAndView();
		List<Fattura> list = fatturaService.getFatturaByData(data, pag);
		model.addObject("listaFatture", list);
		model.setViewName("ritornochiamatafatture");
		return model;
	}

	@GetMapping("/getfatturabyannoform")
	public ModelAndView getFatturaByAnnoForm(@RequestParam Integer anno, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "1000") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		ModelAndView model = new ModelAndView();
		List<Fattura> list = fatturaService.getFatturaByAnno(anno, pag);
		model.addObject("listaFatture", list);
		model.setViewName("ritornochiamatafatture");
		return model;
	}

	@GetMapping("/getfatturabyrangeimportoform")
	public ModelAndView getFatturaByRangeImportoForm(@RequestParam(defaultValue = "0") Double importoMin,
			@RequestParam(defaultValue = "10000000000") Double importoMax,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "1000") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		ModelAndView model = new ModelAndView();
		List<Fattura> list = fatturaService.getFatturaByRangeImporto(importoMin, importoMax, pag);
		model.addObject("listaFatture", list);
		model.setViewName("ritornochiamatafatture");
		return model;
	}
}
	

