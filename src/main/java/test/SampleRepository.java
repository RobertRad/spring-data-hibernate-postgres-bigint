package test;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface SampleRepository extends Repository<Sample, Long> {
	Sample save(Sample sample);

	Optional<Sample> findByName(String name);

	List<Sample> findAll();

	@Query("SELECT id FROM test.Sample")
	List<Long> findAllIds();

	//@Query(value = "SELECT CAST(id AS bigint) FROM sample", nativeQuery = true)
	@Query(value = "SELECT id FROM sample", nativeQuery = true)
	List<Long> findAllIdsNative();

	void delete(Long id);

	void delete(Sample sample);
}
