package br.com.app.rest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.app.AppApplicationTests;
import br.com.app.controller.MainController;

public class RestControllerTest extends AppApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private MainController mainController;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
	}

	@Test
	public void carregaArquivoTest() throws Exception {		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/textoit/"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("Arquivo carregado com sucesso"));
	}

	@Test
	public void testaGetWinnersByYear() throws Exception {
		carregaBase();
		this.mockMvc.perform(MockMvcRequestBuilders.get("/textoit/winners/1986"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(""));
//		{"id":92,"year":1986,"title":"Howard the Duck","studios":[],"producers":[{"id":93,"name":"Gloria Katz"}],"winner":true},{"id":94,"year":1986,"title":"Under the Cherry Moon","studios":[],"producers":[{"id":95,"name":"Bob Cavallo"},{"id":96,"name":"Joe Ruffalo"},{"id":97,"name":"Steve Fargnoli"}],"winner":true}
	}

	private void carregaBase() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/textoit/"));
	}
	
	@Test
	public void testaGetMostWinnersPerYear() throws Exception {
		carregaBase();
		this.mockMvc.perform(MockMvcRequestBuilders.get("/textoit/winners/year"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("[{\"count\":2,\"year\":1986},{\"count\":2,\"year\":1990},{\"count\":2,\"year\":2015}]"));
	}
	
	@Test
	public void testaGetStudiosWinners() throws Exception {
		carregaBase();
		this.mockMvc.perform(MockMvcRequestBuilders.get("/textoit/winners/studio"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(""));
	}
	
	@Test
	public void testaGetIntervalProducersWinners() throws Exception {
		carregaBase();
		this.mockMvc.perform(MockMvcRequestBuilders.get("/textoit/winners/producer/interval"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("{\"min\":{\"producer\":\"Joel Silver\",\"interval\":1,\"previousWin\":\"1990\",\"followingWin\":\"1991\"},\"max\":{\"producer\":\"Matthew Vaughn\",\"interval\":13,\"previousWin\":\"2002\",\"followingWin\":\"2015\"}}"));
	}
	

}
