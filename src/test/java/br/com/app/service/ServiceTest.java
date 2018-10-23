package br.com.app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.app.domain.FilmeVencedorException;
import br.com.app.domain.Movie;
import br.com.app.domain.Movie.MovieBuilder;
import br.com.app.domain.Producer;
import br.com.app.domain.Studio;
import br.com.app.service.MovieService;
import br.com.app.service.ProducerService;
import br.com.app.service.StudioService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ServiceTest {

	@Autowired
	private MovieService movieService;
	@Autowired
	private StudioService studioService;
	@Autowired
	private ProducerService producerService;
	
	@Test
	public void deveSalvarFilme() {
		Movie movie = generateMovie();		
		Movie save = movieService.save(movie);
		assertNotNull(save);
	}

	@Test
	public void deveSalvarStudio() {
		Studio studio = new Studio();
		studio.setName("teste");
		studio.setWinner(true);
		Movie movie = movieService.save(generateMovie());
		studio.addMovie(movie);
		Studio save = studioService.save(studio);
		assertNotNull(save);
	}
	
	@Test
	public void deveSalvarProducer() {
		Producer producer = new Producer();
		producer.setName("teste");
		Movie movie = movieService.save(generateMovie());
		producer.addMovie(movie);
		Producer save = producerService.save(producer);
		assertNotNull(save);
	}
	
	@Test(expected = FilmeVencedorException.class)	
	public void naoDeveExcluirFilmeVencedor() throws FilmeVencedorException {
		Movie movie = movieService.save(generateMovie());
		try {
			movieService.delete(movie.getId());
		} catch (Exception e) {
			assertEquals("Filmes vencedores não podem ser excluidos!", e.getMessage());		
			throw e;
		}
	}

	private Movie generateMovie() {
		MovieBuilder b = new MovieBuilder();
		List<Studio> studios = generateStudios();
		List<Producer> producers = generateProducers();
		b.title("test").year(1994).studios(studios).producers(producers).winner(true);
		return b.build();
	}
	
	private List<Producer> generateProducers() {
		List<Producer> producers = new ArrayList<>();
		Producer producer = new Producer();
		producer.setName("teste");
		producerService.save(producer);
		producers.add(producer);
		return producers;
	}

	private List<Studio> generateStudios() {
		List<Studio> studios = new ArrayList<>();
		Studio studio = new Studio();
		studio.setName("teste");
		studio.setWinner(true);
		studioService.save(studio);
		studios.add(studio);
		return studios;
	}

}
