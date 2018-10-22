package br.com.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.app.domain.Studio;

@Repository
public interface StudioRepository extends CrudRepository<Studio, Long> {
	
	public Studio getStudioByName(String name);
			
}
