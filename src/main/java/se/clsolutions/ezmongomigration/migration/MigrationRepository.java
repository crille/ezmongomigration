package se.clsolutions.ezmongomigration.migration;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MigrationRepository extends MongoRepository<Migration, String> {
}
