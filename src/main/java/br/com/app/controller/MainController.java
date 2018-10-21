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
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.com.app.domain.Movie;
import br.com.app.domain.Movie.MovieBuilder;
import br.com.app.domain.Producer;
import br.com.app.domain.Studio;
import br.com.app.service.MovieService;

@Controller
public class UploadController {
	
	@Autowired
	private MovieService movieService;
	
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
			MovieBuilder builder = new MovieBuilder();
			String year = split[0];
			String title = split[1];
			List<Studio> studios = generateStudio(Arrays.asList(split[2].split(",")));
			List<Producer> producers = generateProducer(Arrays.asList(split[3].split(",")));
			boolean winner = false;
			if (split.length > 4)
				winner = Boolean.valueOf(StringUtils.isNotBlank(split[4]));
			Movie filme = builder.year(Integer.valueOf(year)).title(title).studios(studios)
					.producers(producers).winner(winner).build();
			
			movieService.save(filme);
		}
		
		return "Arquivo carregado com sucesso";
	}
	
	private List<Studio> generateStudio(List<String> studios) {
		return null;
	}
	
	private List<Producer> generateProducer(List<String> producers) {
		return null;
	}

	private void popularListaComDadosDoArquivo(List<String> movies) throws IOException {
		File f = new File(this.getClass().getClassLoader().getResource("files/movielist.csv").getFile());
		Stream<String> lines = Files.lines(f.toPath());
		lines.forEach(line -> movies.add(line));
		lines.close();			
		movies.remove(0);
	}

}
