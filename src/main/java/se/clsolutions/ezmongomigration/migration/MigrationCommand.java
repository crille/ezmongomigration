package se.clsolutions.ezmongomigration.migration;


import java.util.ArrayList;
import java.util.List;


/**
 * Interface for Migration actions, a migration must have a unique id.
 */
public interface MigrationCommand {

   List<Environment.Type> environmentsExcluded = new ArrayList<>();

   String getId();

   String getDescription();

   void execute();

   default boolean isRunOnce() {
      return true;
   };

   default boolean isEnvironmentExcluded(Environment.Type environment) {
      return false;
   };
}
