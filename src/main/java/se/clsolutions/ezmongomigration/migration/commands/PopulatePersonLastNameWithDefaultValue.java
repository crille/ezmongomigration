package se.clsolutions.ezmongomigration.migration.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.clsolutions.ezmongomigration.domain.Person;
import se.clsolutions.ezmongomigration.migration.MigrationCommand;
import se.clsolutions.ezmongomigration.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PopulatePersonLastNameWithDefaultValue implements MigrationCommand {

   public final static String ID = "PERSON_LAST_NAME_IS_LJUNGBLAD";

   @Autowired
   private PersonRepository personRepository;

   @Override
   public String getId() {
      return ID;
   }

   @Override
   public String getDescription() {
      return "Add a new field 'lastName' to object, to have more info about the person";
   }

   @Override
   public void execute() {



      personRepository.deleteAll();
      personRepository.save(new Person("Kalle", "Anka")).block();
      personRepository.save(new Person("Pelle", "Svanslos"));

      List<Person> updatePersons = personRepository
              .findAll().toStream().map(
                      p -> new Person(p.getFirstName(), "Ljungblad"))
              .collect(Collectors.toList());

      personRepository.saveAll(updatePersons);
   }

   @Override
   public boolean isRunOnce() {
      return false;
   }
}
