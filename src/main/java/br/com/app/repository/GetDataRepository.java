package br.com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.app.domain.Movie;
import br.com.app.domain.ProducerWinner;
import br.com.app.domain.StudioWinnerCount;
import br.com.app.domain.WinnersByYear;

public interface GetDataRepository extends CrudRepository<Movie, Long> {

	@Query("select " + "new br.com.app.domain.WinnersByYear(m.year, count(m)) " + "from " + "Movie m "
			+ "where m.winner = 'Y' group by " + "m.year " + "having count(m) > 1")
	public List<WinnersByYear> getWinnersByYear();

	@Query("Select new br.com.app.domain.StudioWinnerCount(s.name, count(s)) \r\n" + "  from Movie m\r\n"
			+ "  join Studio s on s.movie = m\r\n" + " where m.winner = 'Y'\r\n" + " group by s.name")
	public List<StudioWinnerCount> getStudiosByCountWinner();

	@Query("select new br.com.app.domain.ProducerWinner(p.name, m.year) from Movie m join Producer p on p.movie = m where m.winner = 'Y' order by m.year")
	public List<ProducerWinner> getProducersWinners();

}
