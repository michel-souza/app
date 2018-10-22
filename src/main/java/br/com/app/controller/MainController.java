package br.com.app.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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
	public @ResponseBody String index() throws Exception {
		List<String> movies = new ArrayList<>();
		try {
			popularListaComDadosDoArquivo(movies);
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("Erro ao carregar arquivo: " + e.getMessage());
		}
		for (String movie : movies) {
			String[] split = movie.split(";");
			boolean winner = false;
			MovieBuilder builder = new MovieBuilder();
			String year = split[0];
			String title = split[1];
			if (split.length > 4)
				winner = Boolean.valueOf(StringUtils.isNotBlank(split[4]));
			List<Studio> studios = generateStudio(Arrays.asList(split[2].split(",")), winner);
			List<Producer> producers = generateProducer(Arrays.asList(split[3].split(",")));
			Movie filme = builder.year(Integer.valueOf(year)).title(title).studios(studios).producers(producers)
					.winner(winner).build();

			movieService.save(filme);

			studios.forEach(std -> updateMovieFromStudio(filme, std));
			producers.forEach(prod -> updateMovieFromProducer(filme, prod));
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
		return movieService.getStudiosWinners();
	}

	private void updateMovieFromStudio(Movie filme, Studio std) {
		std.setMovie(filme);
		studioService.save(std);
	}

	private void updateMovieFromProducer(Movie filme, Producer producer) {
		producer.setMovie(filme);
		producerService.save(producer);
	}

	private List<Studio> generateStudio(List<String> studios, boolean winner) {
		List<Studio> listStudios = new ArrayList<>();
		for (String studio : studios) {
			Studio studioByName = studioService.getStudioByName(studio);
			if (studioByName == null) {
				Studio std = new Studio();
				std.setName(studio);
				std.setWinner(winner);
				listStudios.add(std);
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
			prod.setName(producer);
			listProducers.add(prod);
		}
		return listProducers;
	}

	private void popularListaComDadosDoArquivo(List<String> movies) throws IOException {
		File f = new File(this.getClass().getClassLoader().getResource("files/movielist.csv").getFile());
		Stream<String> lines = Files.lines(f.toPath());
		lines.forEach(line -> movies.add(line));
		lines.close();
		movies.remove(0);
	}

}
