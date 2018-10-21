package br.com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.app.domain.Studio;
import br.com.app.repository.StudioRepository;

@Service
public class StudioService {
	
	@Autowired
	@Qualifier("studioRepository")
	private StudioRepository studioRepository;
	
	public Studio save(Studio studio) {
		return studioRepository.save(studio);
	}

	
	
	
	
}
