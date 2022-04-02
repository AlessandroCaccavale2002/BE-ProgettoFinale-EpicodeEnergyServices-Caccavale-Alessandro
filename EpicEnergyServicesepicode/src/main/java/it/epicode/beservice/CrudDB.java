package it.epicode.beservice;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.epicode.beservice.controller.AuthController;
import it.epicode.beservice.controller.ClienteController;
import it.epicode.beservice.controller.ComuneController;
import it.epicode.beservice.controller.FatturaController;
import it.epicode.beservice.controller.IndirizzoController;
import it.epicode.beservice.controller.ProvinciaController;
import it.epicode.beservice.controller.RegioneController;
import it.epicode.beservice.model.Cliente;
import it.epicode.beservice.model.Fattura;
import it.epicode.beservice.model.Indirizzo;
import it.epicode.beservice.model.Role;
import it.epicode.beservice.model.RoleType;
import it.epicode.beservice.model.StatoFattura;
import it.epicode.beservice.model.TipoCliente;
import it.epicode.beservice.security.SignupRequest;
import it.epicode.beservice.service.RoleService;
import it.epicode.beservice.service.StatoFatturaService;

@Component
public class CrudDB implements CommandLineRunner{

	@Autowired
	RegioneController regioneController;
	@Autowired
	ProvinciaController provinciaController;
	@Autowired
	ComuneController comuneController;
	@Autowired
	StatoFatturaService statoFatturaService;
	@Autowired
	RoleService roleService;
	@Autowired
	IndirizzoController indirizzoController;
	@Autowired
	ClienteController clienteController;
	@Autowired
	AuthController authController;
	@Autowired
	FatturaController fatturaController;
	
	public void CrudDb() {
		regioneController.caricamentoCsvRegioni();
		provinciaController.caricamentoCsvProvince();
		comuneController.caricamentoCsvComuni();
		
		StatoFattura saldata = new StatoFattura("Saldata");
		StatoFattura nonSaldata = new StatoFattura("Non Saldata");
		statoFatturaService.salvaStatoFattura(saldata);
		statoFatturaService.salvaStatoFattura(nonSaldata);
		
		Role u = new Role(RoleType.ROLE_USER);
		Role a = new Role(RoleType.ROLE_ADMIN);
		roleService.salvaRuolo(u);
		roleService.salvaRuolo(a);
		
		Set<String> ruoli = new HashSet<>();
		ruoli.add("ROLE_ADMIN");
		authController.registerUser(new SignupRequest("Admin", "admin@email.com", ruoli, "password", "AdminName", "AdminSurname"));
		
		Set<String> ruoli2 = new HashSet<>();
		ruoli.add("ROLE_USER");
		authController.registerUser(new SignupRequest("user", "user@email.com", ruoli2, "password", "UserName", "UserSurname"));
		
		
		Indirizzo i1 = new Indirizzo("Via delle Baleniere", 19, "00121", "marittima", comuneController.getComuneByNome("Roma"));
		Indirizzo i2 = new Indirizzo("Via Emilio Morosini", 76, "00153", "centro", comuneController.getComuneByNome("Roma"));
		Indirizzo i3 = new Indirizzo("Via di Santa Melania", 111, "39020", "città", comuneController.getComuneByNome("Oristano"));
		Indirizzo i4 = new Indirizzo("Via Melisurgo", 168, "55018", "dentro le mura", comuneController.getComuneByNome("Lucca"));
		Indirizzo i5 = new Indirizzo("Via Venezia", 167, "87060", "città", comuneController.getComuneByNome("Mirto"));
		Indirizzo i6 = new Indirizzo("Via Francesco Girardi", 107, "09090", "centro", comuneController.getComuneByNome("Palmas Arborea"));
		Indirizzo i7 = new Indirizzo("Via Alessandro Farnese", 58, "39056", "marittima", comuneController.getComuneByNome("Carezzano"));
		Indirizzo i8 = new Indirizzo("Via Francesco Saverio Correra", 95, "12012", "campagna", comuneController.getComuneByNome("Boves"));
		Indirizzo i9 = new Indirizzo("Via Giacinto Gigante", 93, "41057", "centro", comuneController.getComuneByNome("Spilamberto"));
		Indirizzo i10 = new Indirizzo("Corso Porta Nuova", 112, "42016", "centro", comuneController.getComuneByNome("Guastalla"));
		Indirizzo i11 = new Indirizzo("Strada Bresciana", 131, "64020", "periferia", comuneController.getComuneByNome("Pineto"));
		Indirizzo i12 = new Indirizzo("Via Callicratide", 158, "11010", "centro", comuneController.getComuneByNome("Ollomont"));
		Indirizzo i13 = new Indirizzo("Via Carlo Alberto", 153, "220121", "montagna", comuneController.getComuneByNome("Cremia"));
		Indirizzo i14 = new Indirizzo("Via Piave", 109, "66010", "centro", comuneController.getComuneByNome("Musso"));
		Indirizzo i15 = new Indirizzo("Via Santa Maria di Costantinopoli", 135, "46040", "marittima", comuneController.getComuneByNome("Rodigo"));
		Indirizzo i16 = new Indirizzo("Via Francesco Del Giudice", 161, "50030", "montagna", comuneController.getComuneByNome("Pancarana"));
		Indirizzo i17 = new Indirizzo("Via Duomo", 139, "57020", "centro", comuneController.getComuneByNome("Pelago"));
		Indirizzo i18 = new Indirizzo("Via Antonio da Legnago", 37, "89010", "marittima", comuneController.getComuneByNome("Cannara"));
		Indirizzo i19 = new Indirizzo("Via Nazario Sauro", 156, "20012", "centro", comuneController.getComuneByNome("Cornaredo"));
		Indirizzo i20 = new Indirizzo("Via Croce Rossa", 176, "09070", "centro", comuneController.getComuneByNome("Narbolia"));
		indirizzoController.salvaIndirizzo(i1);
		indirizzoController.salvaIndirizzo(i2);
		indirizzoController.salvaIndirizzo(i3);
		indirizzoController.salvaIndirizzo(i4);
		indirizzoController.salvaIndirizzo(i5);
		indirizzoController.salvaIndirizzo(i6);
		indirizzoController.salvaIndirizzo(i7);
		indirizzoController.salvaIndirizzo(i8);
		indirizzoController.salvaIndirizzo(i9);
		indirizzoController.salvaIndirizzo(i10);
		indirizzoController.salvaIndirizzo(i11);
		indirizzoController.salvaIndirizzo(i12);
		indirizzoController.salvaIndirizzo(i13);
		indirizzoController.salvaIndirizzo(i14);
		indirizzoController.salvaIndirizzo(i15);
		indirizzoController.salvaIndirizzo(i16);
		indirizzoController.salvaIndirizzo(i17);
		indirizzoController.salvaIndirizzo(i18);
		indirizzoController.salvaIndirizzo(i19);
		indirizzoController.salvaIndirizzo(i20);
		
		Cliente c1 = new Cliente("Fornace", 1234569l, TipoCliente.SPA, "fornace@email.com", "fornace@pec.com", "56165656", "Berenice", "Ferri", "2646465", "berenice@email.com", i1, i1, LocalDate.now(), LocalDate.of(2020, 10, 3), 20000.00);
		Cliente c2 =new Cliente("Edimedia Grafica e Comunicazione", 198731l,TipoCliente.SRL,"edimedia@gmail.com","edimedia@pec.com","06296423","Davide","Arnaldo","03835924188","davide@gmail.com",i2,i2, LocalDate.now(), LocalDate.of(2021,11,13),120000.0);
		Cliente c3 = new Cliente("Web Marketing Trento", 68468463l, TipoCliente.PA, "wmt@email.com", "wmt@pec.com", "14651656", "Settimo", "Rossi", "1232453", "settimo@email.com", i3, i3, LocalDate.now(), LocalDate.of(20201, 12, 15), 15000.00);
		Cliente c4 = new Cliente("Assistenzaoc", 688484l, TipoCliente.SAS, "assistenzaoc@email.com", "assistenzaoc@pec.com", "447453543", "Flaviana", "Lucciano", "8737833", "f.lucciano@email.com", i4, i4, LocalDate.now(), LocalDate.of(2019, 7, 12), 12000.00);
		Cliente c5 = new Cliente("Speedtech", 4864654685l, TipoCliente.SAS, "speedtech@email.com", "speedtech@pec.com", "787353543", "Bianca", "Trentino", "8734543", "bianca@email.com", i5, i5, LocalDate.now(), LocalDate.of(2020, 9, 20), 13500.00);
		Cliente c6 = new Cliente("Esakon", 656446465l, TipoCliente.SPA, "esakon@email.com", "esakon@pec.com", "4533737", "Arsenio", "Angelo", "123537", "lupin@email.com", i6, i6, LocalDate.now(), LocalDate.of(2020, 11, 14), 14200.00);
		Cliente c7 = new Cliente("Into Sistem", 4565416545l, TipoCliente.PA, "into@email.com", "into@pec.com", "78377387", "Lionela", "Benvenuti", "3575373", "l.benvenuti@email.com", i7, i7, LocalDate.now(), LocalDate.of(2021, 6, 8), 21000.00);
		Cliente c8 = new Cliente("Infol Informatica", 516548656l, TipoCliente.SRL, "infol@email.com", "infol@pec.com", "457837", "Elisa", "Baresi", "753353432", "elisa@email.com", i8, i8, LocalDate.now(), LocalDate.of(2020, 4, 3), 31000.00);
		Cliente c9 = new Cliente("Cherry - Computer", 6544656446l, TipoCliente.SPA, "cherry@email.com", "cherry@pec.com", "837786", "Antonella", "Toscani", "3577378", "antonella@email.com", i9, i9, LocalDate.now(), LocalDate.of(2020, 5, 28), 120000.00);
		Cliente c10 = new Cliente("Stefania Zilio", 5615461568l, TipoCliente.SAS, "sz@email.com", "sz@pec.com", "7837833", "Amalio", "Cattaneo", "12343737", "cattaneo@email.com", i10, i10, LocalDate.now(), LocalDate.of(2018, 7, 15), 75000.00);
		Cliente c11 = new Cliente("ESmedia", 61846985664l, TipoCliente.PA, "esmedia@email.com", "esmedia@pec.com", "7837", "Chiara", "Iadanza", "43437537", "chiara@email.com", i11, i11, LocalDate.now(), LocalDate.of(2020, 4, 11), 10000.00);
		Cliente c12 = new Cliente("Davide Prosser web agency", 65441656l, TipoCliente.SRL, "dpwa@email.com", "dpwa@pec.com", "112357", "Elio", "Esposito", "34523755", "elio@email.com", i12, i12, LocalDate.now(), LocalDate.of(2021, 4, 9), 16000.00);
		Cliente c13 = new Cliente("Local OP", 54654654162l, TipoCliente.PA, "op@email.com", "op@pec.com", "87378334", "Giraldo", "Piazza", "12345357", "giraldo@email.com", i13, i13, LocalDate.now(), LocalDate.of(2019, 1, 24), 36000.00);
		Cliente c14 = new Cliente("Trentin Rossano", 35415416565l, TipoCliente.SRL, "rossano@email.com", "rossano@pec.com", "12345378", "Gioacchina", "Esposito", "23453757", "gioacchina@email.com", i14, i14, LocalDate.now(), LocalDate.of(2020, 2, 12), 18623.00);
		Cliente c15 = new Cliente("Sgarbi Digital Marketing", 645668546l, TipoCliente.SAS, "sdm@email.com", "sdm@pec.com", "87635411", "Galileo", "Napolitani", "3427375", "galileo@email.com", i15, i15, LocalDate.now(), LocalDate.of(2020, 6, 30), 34526.00);
		Cliente c16 = new Cliente("Lpe Production", 544541651l, TipoCliente.SPA, "lep@email.com", "lpe@pec.com", "3457833", "Ottavio", "Lettiere", "21343757", "ottavio@email.com", i16, i16, LocalDate.now(), LocalDate.of(2021, 5, 5), 12536.00);
		Cliente c17 = new Cliente("Geeksolution", 6516541656l, TipoCliente.PA, "geeksolution@email.com", "geeksolution@pec.com", "717853", "Alice", "Pantaleone", "42375378573", "alice@email.com", i17, i17, LocalDate.now(), LocalDate.of(2018, 7, 7), 32514.00);
		Cliente c18 = new Cliente("Weber Gabriele", 65416546l, TipoCliente.SPA, "wg@email.com", "wg@pec.com", "24352", "Espedito", "Calabresi", "537537", "calabresi@email.com", i18, i18, LocalDate.now(), LocalDate.of(2020, 2, 15), 124563.00);
		Cliente c19 = new Cliente("Italiaonline Web Agency", 5864454646l, TipoCliente.SRL, "iwa@email.com", "iwa@pec.com", "15052100", "Napoleone", "Udinese", "7357537", "napoleons@email.com", i19, i19, LocalDate.now(), LocalDate.of(2021, 10, 3), 12458.00);
		Cliente c20 = new Cliente("Dunder Mifflin", 1234569l, TipoCliente.SAS, "ddm@email.com", "ddm@pec.com", "56165656", "Micheal", "Scott", "2646465", "m.scott@email.com", i20, i20, LocalDate.now(), LocalDate.of(2020, 10, 3), 20000.05);
		clienteController.salvaCliente(c1);
		clienteController.salvaCliente(c2);
		clienteController.salvaCliente(c3);
		clienteController.salvaCliente(c4);
		clienteController.salvaCliente(c5);
		clienteController.salvaCliente(c6);
		clienteController.salvaCliente(c7);
		clienteController.salvaCliente(c8);
		clienteController.salvaCliente(c9);
		clienteController.salvaCliente(c10);
		clienteController.salvaCliente(c11);
		clienteController.salvaCliente(c12);
		clienteController.salvaCliente(c13);
		clienteController.salvaCliente(c14);
		clienteController.salvaCliente(c15);
		clienteController.salvaCliente(c16);
		clienteController.salvaCliente(c17);
		clienteController.salvaCliente(c18);
		clienteController.salvaCliente(c19);
		clienteController.salvaCliente(c20);
		
		Fattura f1 = new Fattura(LocalDate.of(2021, 10, 15), 123456l, 2000.00, saldata, c1);
		Fattura f2 = new Fattura(LocalDate.of(2020, 2, 24), 123432l, 300.00, nonSaldata, c1);
		Fattura f3 = new Fattura(LocalDate.of(2021, 7, 15), 9655l, 970.12, saldata, c2);
		Fattura f4 = new Fattura(LocalDate.of(2021, 3, 2), 6548l, 10450.00, nonSaldata, c2);
		Fattura f5 = new Fattura(LocalDate.of(2018, 1, 16), 123456l, 12345.00, saldata, c3);
		Fattura f6 = new Fattura(LocalDate.of(2021, 7, 30), 54654l, 3651.00, nonSaldata, c3);
		Fattura f7 = new Fattura(LocalDate.of(2012, 7, 4), 566516l, 326.00, saldata, c4);
		Fattura f8 = new Fattura(LocalDate.of(2014, 11, 9), 21561l, 7532.00, nonSaldata, c4);
		Fattura f9 = new Fattura(LocalDate.of(2021, 3, 16), 31516l, 1245.00, nonSaldata, c5);
		Fattura f10 = new Fattura(LocalDate.of(2018, 10, 18), 1564l, 5465.00, saldata, c5);
		Fattura f11 = new Fattura(LocalDate.of(2020, 7, 15), 23565l, 3565.00, nonSaldata, c6);
		Fattura f12 = new Fattura(LocalDate.of(2021, 3, 20), 94565l, 102.00, saldata, c6);
		Fattura f13 = new Fattura(LocalDate.of(2021, 11, 25), 1256l, 320.00, saldata, c7);
		Fattura f14 = new Fattura(LocalDate.of(2021, 8, 5), 12345l, 420.00, saldata, c7);
		Fattura f15 = new Fattura(LocalDate.of(2020, 4, 24), 4235l, 7546.00, saldata, c8);
		Fattura f16 = new Fattura(LocalDate.of(2018, 12, 31), 24557l, 12125.00, saldata, c8);
		Fattura f17 = new Fattura(LocalDate.of(2021, 4, 7), 45783l, 2230.00, nonSaldata, c9);
		Fattura f18 = new Fattura(LocalDate.of(2021, 6, 3), 47537l, 547.00, nonSaldata, c9);
		Fattura f19 = new Fattura(LocalDate.of(2019, 3, 12), 354165l, 22000.00, saldata, c10);
		Fattura f20 = new Fattura(LocalDate.of(2020, 7, 15), 15166l, 1245.00, nonSaldata, c10);
		Fattura f21 = new Fattura(LocalDate.of(2021, 3, 4), 24276l, 422.00, nonSaldata, c11);
		Fattura f22 = new Fattura(LocalDate.of(2021, 4, 21), 2738l, 412583.00, saldata, c11);
		Fattura f23 = new Fattura(LocalDate.of(2021, 12, 15), 154556l, 3400.00, saldata, c12);
		Fattura f24 = new Fattura(LocalDate.of(2021, 10, 24), 36556l, 2200.00, saldata, c12);
		Fattura f25 = new Fattura(LocalDate.of(2018, 7, 24), 7377l, 75564.00, saldata, c13);
		Fattura f26 = new Fattura(LocalDate.of(2019, 9, 12), 12357l, 42.00, nonSaldata, c13);
		Fattura f27 = new Fattura(LocalDate.of(2021, 7, 15), 123456l, 2000.00, saldata, c14);
		Fattura f28 = new Fattura(LocalDate.of(2020, 8, 20), 123456l, 2000.00, saldata, c14);
		Fattura f29 = new Fattura(LocalDate.of(2021, 9, 4), 456l, 31235.00, nonSaldata, c15);
		Fattura f30 = new Fattura(LocalDate.of(2021, 10, 6), 56l, 3225.00, saldata, c15);
		Fattura f31 = new Fattura(LocalDate.of(2028, 3, 21), 121l, 545.00, saldata, c16);
		Fattura f32 = new Fattura(LocalDate.of(2021, 5, 23), 856l, 5.00, saldata, c16);
		Fattura f33 = new Fattura(LocalDate.of(2022, 9, 9), 48946l, 6584.00, saldata, c17);
		Fattura f34 = new Fattura(LocalDate.of(2024, 3, 4), 1l, 1235.00, saldata, c17);
		Fattura f35 = new Fattura(LocalDate.of(2021, 10, 30), 5166l, 230.00, saldata, c18);
		Fattura f36 = new Fattura(LocalDate.of(2021, 3, 9), 10l, 36.00, saldata, c18);
		Fattura f37 = new Fattura(LocalDate.of(2021, 6, 3), 13l, 256.00, saldata, c19);
		Fattura f38 = new Fattura(LocalDate.of(2021, 11, 15), 163l, 986.00, saldata, c19);
		Fattura f39 = new Fattura(LocalDate.of(2021, 12, 25), 123456l, 2000.00, saldata, c20);
		Fattura f40 = new Fattura(LocalDate.of(2021, 12, 31), 123456l, 2000.00, saldata, c20);
		fatturaController.salvaFattura(f1);
		fatturaController.salvaFattura(f2);
		fatturaController.salvaFattura(f3);
		fatturaController.salvaFattura(f4);
		fatturaController.salvaFattura(f5);
		fatturaController.salvaFattura(f6);
		fatturaController.salvaFattura(f7);
		fatturaController.salvaFattura(f8);
		fatturaController.salvaFattura(f9);
		fatturaController.salvaFattura(f10);
		fatturaController.salvaFattura(f11);
		fatturaController.salvaFattura(f12);
		fatturaController.salvaFattura(f13);
		fatturaController.salvaFattura(f14);
		fatturaController.salvaFattura(f15);
		fatturaController.salvaFattura(f16);
		fatturaController.salvaFattura(f17);
		fatturaController.salvaFattura(f18);
		fatturaController.salvaFattura(f19);
		fatturaController.salvaFattura(f20);
		fatturaController.salvaFattura(f21);
		fatturaController.salvaFattura(f22);
		fatturaController.salvaFattura(f23);
		fatturaController.salvaFattura(f24);
		fatturaController.salvaFattura(f25);
		fatturaController.salvaFattura(f26);
		fatturaController.salvaFattura(f27);
		fatturaController.salvaFattura(f28);
		fatturaController.salvaFattura(f29);
		fatturaController.salvaFattura(f30);
		fatturaController.salvaFattura(f31);
		fatturaController.salvaFattura(f32);
		fatturaController.salvaFattura(f33);
		fatturaController.salvaFattura(f34);
		fatturaController.salvaFattura(f35);
		fatturaController.salvaFattura(f36);
		fatturaController.salvaFattura(f37);
		fatturaController.salvaFattura(f38);
		fatturaController.salvaFattura(f39);
		fatturaController.salvaFattura(f40);
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		CrudDb();
		System.out.println("PRONTO by Caccavale Alessandro :)");
	}

}
