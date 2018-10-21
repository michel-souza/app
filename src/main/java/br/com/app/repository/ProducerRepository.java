package br.com.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.app.domain.Producer;

@Repository
public interface ProducerRepository extends CrudRepository<Producer, Long> {
			
}
