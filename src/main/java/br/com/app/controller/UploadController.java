package br.com.app.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.com.app.model.Movie;
import br.com.app.model.Movie.MovieBuilder;
import br.com.app.repository.MovieRepository;

@Controller
public class UploadController {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String uploadArquivo(@RequestParam(value = "file") MultipartFile file) throws Exception {
		List<String> movies = new ArrayList<>();
		try {
			File f = new File(file.getOriginalFilename());
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(file.getBytes());
			fos.close();
			Files.lines(f.toPath()).forEach(line -> movies.add(line));
			Files.lines(f.toPath()).close();			
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("Erro ao carregar arquivo: " + e.getMessage());
		}
		for (String movie : movies) {
			String[] split = movie.split(";");
			MovieBuilder builde = new MovieBuilder();
			String year = split[0];
			String title = split[1];
			List<String> studios = Arrays.asList(split[2].split(","));
			List<String> producers = Arrays.asList(split[3].split(","));
			Boolean winner = Boolean.valueOf(StringUtils.isNotBlank(split[4]));
			Movie filme = builde.year(Integer.valueOf(year)).title(title).studios(studios)
					.producers(producers).winner(winner).build();
			
			movieRepository.save(filme);
		}
		
		return "Arquivo carregado com sucesso";
	}

}
