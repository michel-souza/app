package br.com.app.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.app.domain.Movie;
import br.com.app.domain.Movie.MovieBuilder;
import br.com.app.domain.Producer;
import br.com.app.domain.ProducerIntervalWinner;
import br.com.app.domain.Studio;
import br.com.app.domain.StudioWinnerCount;
import br.com.app.domain.WinnersByYear;
import br.com.app.service.MovieService;
import br.com.app.service.ProducerService;
import br.com.app.service.StudioService;

@Controller
@RequestMapping(value = "/textoit")
public class MainController {

	@Autowired
	private MovieService movieService;
	@Autowired
	private StudioService studioService;
	@Autowired
	private ProducerService producerService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody String index() {
		List<String> movies = new ArrayList<>();
			popularListaComDadosDoArquivo(movies);
		
		for (String movie : movies) {
			String[] split = movie.split(";");
			boolean winner = false;
			MovieBuilder builder = new MovieBuilder();
			String year = split[0];
			String title = split[1];
			if (split.length > 4)
				winner = Boolean.valueOf(StringUtils.isNotBlank(split[4]));
			List<Studio> studios = generateStudio(Arrays.asList(split[2].split(",")));
			List<Producer> producers = generateProducer(Arrays.asList(split[3].split(",|\\ and")));
			Movie filme = builder.year(Integer.valueOf(year)).title(title).studios(studios).producers(producers)
					.winner(winner).build();
			if (movieService.findByTitle(title) == null) {
				movieService.save(filme);
			}
		}		
		return "Arquivo carregado com sucesso";
	}

	@RequestMapping(value = "/winners/{ano}", method = RequestMethod.GET)
	public @ResponseBody List<Movie> getWinnersByYear(@PathVariable("ano") String ano) {
		return movieService.getWinnerByYear(Integer.valueOf(ano));
	}

	@RequestMapping(value = "/winners/year", method = RequestMethod.GET)
	public @ResponseBody List<WinnersByYear> getMostWinnersPerYear() {
		return movieService.getWinnersByYear();
	}

	@RequestMapping(value = "/winners/studio", method = RequestMethod.GET)
	public @ResponseBody List<StudioWinnerCount> getStudiosWinners() {
		return studioService.getStudiosWinners();
	}
	
	@RequestMapping(value = "/winners/producer/interval", method = RequestMethod.GET)
	public @ResponseBody ProducerIntervalWinner getIntervalProducersWinners() {
		return producerService.getProducersWinners();
	}
	
	@RequestMapping(value = "/movie/del", method = RequestMethod.DELETE)
	public @ResponseBody String deleteMovie(@PathParam("id") String id) throws Exception {
		movieService.delete(Long.valueOf(id));
		return "Filme deletado com sucesso!";
	}

	private List<Studio> generateStudio(List<String> studios) {
		List<Studio> listStudios = new ArrayList<>();
		for (String studio : studios) {
			Studio studioByName = studioService.getStudioByName(studio);
			if (studioByName == null) {
				Studio std = new Studio();
				std.setName(studio);
				Studio save = studioService.save(std);
				listStudios.add(save);				
			} else {
				listStudios.add(studioByName);
			}
		}
		return listStudios;
	}

	private List<Producer> generateProducer(List<String> producers) {
		List<Producer> listProducers = new ArrayList<>();		
		
		for (String producer : producers) {
			Producer prod = new Producer();
			prod.setName(producer.trim());
			Producer save = producerService.save(prod);
			listProducers.add(save);
		}
		return listProducers;
	}

	private void popularListaComDadosDoArquivo(List<String> movies) {
		
		try(InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("files/movielist.csv");
				BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				movies.add(line);			
			}		 		 
			
		} catch (Exception e) {			
			e.printStackTrace();
		}		
		movies.remove(0);
	}

}
