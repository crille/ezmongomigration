package se.clsolutions.ezmongomigration.migration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static se.clsolutions.ezmongomigration.migration.Environment.getEnvironment;


@Service
public class MigrationService {

   private Logger logger = LoggerFactory.getLogger(MigrationService.class);

   @Value("${environment}")
   private String environment;

   @Value("${dev.migration.on}")
   private String isMigrationUnderDevelopmentOn;

   @Autowired
   private MigrationRepository migrationRepository;

   @Autowired
   private Collection<MigrationCommand> migrations;


   @PostConstruct
   public void executeAllMigrations() {

      if("false".equalsIgnoreCase(isMigrationUnderDevelopmentOn)) {
         log("No migration will be run since 'dev.migration.on=false' in services.local.properties");
         return;
      }

      setEnvironment(environment);

      log("Start migration lookup, find migrations to execute....");
      List<Migration> existingMigrations = migrationRepository.findAll();

      for (MigrationCommand migration : migrations) {

         if(migration.isEnvironmentExcluded(getEnvironment())) {
            log(String.format("Task (%s) is not configured to run for environment (%s)", migration.getId(), getEnvironment()));
            continue;
         }

         if (!migrationAlreadyExecutedSuccessfully(existingMigrations, migration)) {
            executeMigrationCommand(migration);
         }
      }
   }

   private boolean migrationAlreadyExecutedSuccessfully(List<Migration> existingMigrations, MigrationCommand migration) {
      boolean migrationExists = false;


      Optional<Migration> lastExecutedMigration = existingMigrations.stream()
              .filter(existingMigration -> existingMigration.getMigrationId().equals(migration.getId()))
              .max((m1, m2) -> m1.getStartTime().compareTo(m2.getStartTime()));

      if (lastExecutedMigration.isPresent()){
         migrationExists = lastExecutedMigration.get().isSuccess();
      }

      log("Verifying migration: " + migration.getId());
      log(migration.getId() + " already executed: " + migrationExists);
      log(migration.getId() + " run once only: " + migration.isRunOnce());
      return migrationExists && migration.isRunOnce();
   }

   public void executeMigrationCommand(MigrationCommand migrationCommand) {
      LocalDateTime start = LocalDateTime.now();
      try {
         migrationCommand.execute();
         saveMigrationLog(migrationCommand, start, LocalDateTime.now(), true, "OK");
         log("Successfully executed migration: " + migrationCommand.getId());
      } catch (Exception exception) {
         exception.printStackTrace();
         String errorMsg = String.format("ERROR: %s did not finish successfully,%s", migrationCommand.getId(), exception.getMessage());
         logError(errorMsg, exception);
         saveMigrationLog(migrationCommand, start, null, false, errorMsg);
      }
   }

   private void saveMigrationLog(MigrationCommand migrationCommand, LocalDateTime start, LocalDateTime end, boolean success, String message) {
      Migration migration = new Migration();
      migration.setMigrationId(migrationCommand.getId());
      migration.setRunOnce(migrationCommand.isRunOnce());
      migration.setDescription(migrationCommand.getDescription());
      migration.setStartTime(start);
      migration.setEndTime(end);
      migration.setEnvironment(environment);
      migration.setSuccess(success);
      migration.setMessage(message);
      migrationRepository.save(migration);
   }

   private void log(String message) {
      logger.info(message);
      System.out.println(message);
   }

   private void logError(String errorMsg, Exception ex) {
      logger.info(errorMsg, ex);
      System.out.println(errorMsg);
   }

   public List<Migration> findAll() {
      return migrationRepository.findAll();
   }


//   @VisibleForTesting
   void setMigrations(Collection<MigrationCommand> migrations) {
      this.migrations = migrations;
   }

//   @VisibleForTesting
   public void setEnvironment(String environment) {
      this.environment = environment;
   }

}
