package se.clsolutions.ezmongomigration.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import se.clsolutions.ezmongomigration.domain.Person;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
}
