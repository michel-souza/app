package br.com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.domain.Studio;
import br.com.app.domain.StudioWinnerCount;
import br.com.app.repository.GetDataRepository;
import br.com.app.repository.StudioRepository;

@Service
public class StudioService {
	
	@Autowired
	private StudioRepository studioRepository;
	@Autowired
	private GetDataRepository getDataRepository;
	
	public Studio save(Studio studio) {
		return studioRepository.save(studio);
	}
	
	public Studio getStudioByName(String name) {
		return studioRepository.getStudioByName(name);
	}
	
	public List<StudioWinnerCount> getStudiosWinners() {
		return getDataRepository.getStudiosByCountWinner();
	}

	
	
	
	
}
