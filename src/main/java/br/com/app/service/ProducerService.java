package br.com.app.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.domain.Producer;
import br.com.app.domain.ProducerIntervalWinner;
import br.com.app.domain.ProducerWinner;
import br.com.app.repository.GetDataRepository;
import br.com.app.repository.ProducerRepository;

@Service
public class ProducerService {
	
	@Autowired	
	private ProducerRepository producerRepository;
	@Autowired GetDataRepository dataRepository;
	
	public Producer save(Producer studio) {
		return producerRepository.save(studio);
	}
	
	public ProducerIntervalWinner getProducersWinners() {
		ProducerIntervalWinner producerIntervalWinner = new ProducerIntervalWinner();
		HashMap<String, ProducerWinner> hashWinners = new HashMap<>();
		
		
		for (ProducerWinner p : dataRepository.getProducersWinners()) {
			if (hashWinners.containsKey(p.getProducerName())) {
				hashWinners.get(p.getProducerName()).addYear(Long.valueOf(p.getYearWinner()));				
			} else {
				hashWinners.put(p.getProducerName(), p);
			}			
		}
		ProducerIntervalWinner result = new ProducerIntervalWinner();		
		for (ProducerWinner prod : hashWinners.values()) {
			if (prod.getYears().size() > 1) {
				Long ultimo = 0L;
				for (Long year : prod.getYears()) {
					Long intervalo = year - ultimo;
					createMinMax(result, prod, ultimo, year, intervalo);
					ultimo = year;
				}
			}
		}
		producerIntervalWinner.setMin(result.getMin());
		producerIntervalWinner.setMax(result.getMax());
		
		return producerIntervalWinner;
		
	}

	private void createMinMax(ProducerIntervalWinner result, ProducerWinner prod, Long ultimo, Long year,
			Long intervalo) {
		if (result.getMin().getInterval() == 0 ||  intervalo < result.getMin().getInterval()) {
			result.getMin().setInterval(intervalo.intValue());
			result.getMin().setFollowingWin(year.toString());
			result.getMin().setPreviousWin(ultimo.toString());
			result.getMin().setProducer(prod.getProducerName());
		} else if (year.compareTo(intervalo) > 0){						
			result.getMax().setInterval(intervalo.intValue());
			result.getMax().setFollowingWin(year.toString());
			result.getMax().setPreviousWin(ultimo.toString());
			result.getMax().setProducer(prod.getProducerName());
		}
	}

	
	
	
	
}
