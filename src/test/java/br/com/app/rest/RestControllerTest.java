package br.com.app.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.AppApplicationTests;
import br.com.app.controller.MainController;
import br.com.app.exception.FilmeNaoEncontradoException;
import br.com.app.exception.FilmeVencedorException;

@Transactional
@Rollback
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class RestControllerTest extends AppApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private MainController mainController;
	
	@Before
	@Test
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
		this.mockMvc.perform(MockMvcRequestBuilders.get("/texoit/")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Arquivo carregado com sucesso"));
	}

	@Test
	public void testaGetWinnersByYear() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/texoit/winners/1986"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.string("[{\"id\":93,\"year\":1986,\"title\":\"Howard the Duck\",\"studios\":[{\"id\":14,\"name\":\"Universal Studios\",\"winner\":false}],"
								+ "\"producers\":[{\"id\":92,\"name\":\"Gloria Katz\"}],\"winner\":true},{\"id\":97,\"year\":1986,\"title\":\"Under the Cherry Moon\","
								+ "\"studios\":[{\"id\":61,\"name\":\"Warner Bros.\",\"winner\":false}],\"producers\":[{\"id\":94,\"name\":\"Bob Cavallo\"},"
								+ "{\"id\":95,\"name\":\"Joe Ruffalo\"},{\"id\":96,\"name\":\"Steve Fargnoli\"}],\"winner\":true}]"));
	}

	@Test
	public void testaGetMostWinnersPerYear() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/texoit/winners/year"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string(
						"[{\"winnerCount\":2,\"year\":1986},{\"winnerCount\":2,\"year\":1990},{\"winnerCount\":2,\"year\":2015}]"));
	}

	@Test
	public void testaGetStudiosWinners() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/texoit/winners/studio"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.string("[{\"winCount\":6,\"name\":\"Columbia Pictures\"},{\"winCount\":6,\"name\":\"Paramount Pictures\"},"
								+ "{\"winCount\":5,\"name\":\"Warner Bros.\"},{\"winCount\":4,\"name\":\"20th Century Fox\"},{\"winCount\":3,\"name\":\"MGM\"},"
								+ "{\"winCount\":2,\"name\":\"Universal Studios\"},{\"winCount\":2,\"name\":\"Hollywood Pictures\"},"
								+ "{\"winCount\":1,\"name\":\" Nickelodeon Movies\"},{\"winCount\":1,\"name\":\" C2 Pictures\"},"
								+ "{\"winCount\":1,\"name\":\"Summit Entertainment\"},{\"winCount\":1,\"name\":\" Hasbro\"},"
								+ "{\"winCount\":1,\"name\":\"Associated Film Distribution\"},{\"winCount\":1,\"name\":\" Revolution Studios\"},"
								+ "{\"winCount\":1,\"name\":\"First Look Pictures\"},{\"winCount\":1,\"name\":\"Universal Pictures\"},"
								+ "{\"winCount\":1,\"name\":\" Focus Features\"},{\"winCount\":1,\"name\":\"Cannon Films\"},"
								+ "{\"winCount\":1,\"name\":\" United Artists\"},{\"winCount\":1,\"name\":\"Touchstone Pictures\"},"
								+ "{\"winCount\":1,\"name\":\"Samuel Goldwyn Films\"},{\"winCount\":1,\"name\":\"Quality Flix\"},"
								+ "{\"winCount\":1,\"name\":\"TriStar Pictures\"},{\"winCount\":1,\"name\":\" Franchise Pictures\"},"
								+ "{\"winCount\":1,\"name\":\"Relativity Media\"},{\"winCount\":1,\"name\":\" Castle Rock Entertainment\"},"
								+ "{\"winCount\":1,\"name\":\"Screen Gems\"},{\"winCount\":1,\"name\":\"Triumph Releasing\"},{\"winCount\":1,\"name\":\" DreamWorks\"}]"));
	}

	@Test
	public void testaGetIntervalProducersWinners() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/texoit/winners/producer/interval"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.string("{\"min\":{\"producer\":\"Joel Silver\",\"interval\":1,\"previousWin\":\"1990\",\"followingWin\":\"1991\"},"
								+ "\"max\":{\"producer\":\"Matthew Vaughn\",\"interval\":13,\"previousWin\":\"2002\",\"followingWin\":\"2015\"}}"));
	}

	@Test(expected = FilmeVencedorException.class)
	public void testaDeleteMovieFilmeVencedor() throws Throwable {
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.delete("/texoit/movie/del").param("id", "3"));
		} catch (Exception e) {
			assertEquals("Filmes vencedores não podem ser excluidos!", e.getCause().getMessage());		
			throw e.getCause();
		}
	}
	
	@Test
	public void testaDeleteMovieIsOK() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/texoit/movie/del").param("id", "7"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Filme deletado com sucesso!"));
	}
	
	
	@Test(expected = FilmeNaoEncontradoException.class)
	public void testaDeleteMovieFilmeNaoEncontrado() throws Throwable {
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.delete("/texoit/movie/del").param("id", "99999"));
		} catch (Exception e) {
			assertEquals("Filme não encontrado!", e.getCause().getMessage());		
			throw e.getCause();
		}
	}
	
}
