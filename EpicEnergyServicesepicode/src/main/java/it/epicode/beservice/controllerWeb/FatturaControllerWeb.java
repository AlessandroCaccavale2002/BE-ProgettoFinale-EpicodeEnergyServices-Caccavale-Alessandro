package it.epicode.beservice.controllerWeb;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import it.epicode.beservice.model.Fattura;
import it.epicode.beservice.service.ClienteService;
import it.epicode.beservice.service.FatturaService;
import it.epicode.beservice.service.StatoFatturaService;
@Controller
@RequestMapping("/fatturacontrollerweb")
public class FatturaControllerWeb {
	
	@Autowired
	FatturaService fatturaService;
	@Autowired
	StatoFatturaService statoFatturaService;
	@Autowired
	ClienteService clienteService;

			
			@RequestMapping("/salvafatturaform")
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
			
			
			@RequestMapping("/eliminafatturaform")
			public void eliminaFatturaForm(@RequestParam Long id) {
				fatturaService.eliminaFattura(id);
			}
			
			
			@RequestMapping("/getfatturabyclienteform")
			public ModelAndView getFatturaByClienteForm(@RequestParam String ragioneSociale,
					@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "1000") Integer size) {
				Pageable pag = PageRequest.of(page, size);
				ModelAndView model = new ModelAndView();
				List<Fattura> list = fatturaService.getFatturaByCliente(ragioneSociale, pag);
				model.addObject("listaFatture", list);
				model.setViewName("ritornochiamatafatture");
				return model;
			}

			
			@RequestMapping("/getfatturabystatofatturaform")
			public ModelAndView getFatturaByStatoFatturaForm(@RequestParam String statoFattura,
					@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "1000") Integer size) {
				Pageable pag = PageRequest.of(page, size);
				ModelAndView model = new ModelAndView();
				List<Fattura> list = fatturaService.getFatturaByStatoFattura(statoFattura, pag);
				model.addObject("listaFatture", list);
				model.setViewName("ritornochiamatafatture");
				return model;
			}

			
			@RequestMapping("/getfatturabydataform")
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

			
			@RequestMapping("/getfatturabyrangeimportoform")
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


	

	



		

		

