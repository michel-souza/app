package br.com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.domain.Producer;
import br.com.app.repository.ProducerRepository;

@Service
public class ProducerService {
	
	@Autowired	
	private ProducerRepository producerRepository;
	
	public Producer save(Producer studio) {
		return producerRepository.save(studio);
	}

	
	
	
	
}
